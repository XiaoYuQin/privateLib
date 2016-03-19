package com.qinxiaoyu.lib.module.transmit.net.udp.EXAMPLE;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;


public class NioServer {


    private static final int TIMEOUT = 4000; // 超时 (毫秒)  
    private static final int CAPACITY = 255;  
  
    public static void main(String[] args) throws IOException {  
        args = new String[1];  
        args[0] = "4451";  
  
        Selector sel = Selector.open(); 								// 创建选择器，可以处理多路通道。  
  
        DatagramChannel channel = DatagramChannel.open();  
        channel.configureBlocking(false);  
        channel.socket().bind(new InetSocketAddress(8300)); 		// 通道关联的socket绑定地址  
        channel.register(sel, SelectionKey.OP_READ, new ClientData());  
  
        while (true) { 													// 持续运行，接收和返回数据  
            if (sel.select(TIMEOUT) == 0) {  
                System.out.println("No I/O needs to be processed");  
                continue;  
            }  
  
            Iterator<SelectionKey> iter = sel.selectedKeys().iterator(); // 获取可操作的选择键集合  
            while (iter.hasNext()) {  
                SelectionKey key = iter.next(); 						// 键为位掩码  
  
                if (key.isReadable()) { 								// 客户端有数据发送过来  
                    handleRead(key);  
                }  
  
                if (key.isValid() && key.isWritable()) { 				// 通道正常，且客户端需要响应  
                    handleWrite(key);  
                }  
  
                iter.remove(); 											// 从集合中移除选择键  
            }  
  
        }  
    }  
  
    private static void handleRead(SelectionKey key) throws IOException {  
        DatagramChannel channel = (DatagramChannel) key.channel();  
        ClientData clntDat = (ClientData) key.attachment();  
        clntDat.buffer.clear();  
        clntDat.clientAddress = channel.receive(clntDat.buffer); 		// 获取客户端的地址，用以发送响应  
        if (clntDat.clientAddress != null) {							// 接收到数据  
            key.interestOps(SelectionKey.OP_WRITE); 					// 关注客户端读取响应  
            System.out.println("关注客户端读取响应");
        }  
    }  
  
    private static void handleWrite(SelectionKey key) throws IOException {  
        DatagramChannel channel = (DatagramChannel) key.channel();  
        ClientData clntDat = (ClientData) key.attachment();  
        clntDat.buffer.flip(); 											// 从起始位置开始发送  
        int bytesSent = channel.send(clntDat.buffer, clntDat.clientAddress);  
        if (bytesSent != 0) {  
            key.interestOps(SelectionKey.OP_READ); 						// 关注客户端发送数据  
            System.out.println("关注客户端发送数据");
        }  
    }  
  
    public static class ClientData {  
        public SocketAddress clientAddress;  
        public ByteBuffer buffer = ByteBuffer.allocate(CAPACITY);  
    }  
	
	
}
