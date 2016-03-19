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


    private static final int TIMEOUT = 4000; // ��ʱ (����)  
    private static final int CAPACITY = 255;  
  
    public static void main(String[] args) throws IOException {  
        args = new String[1];  
        args[0] = "4451";  
  
        Selector sel = Selector.open(); 								// ����ѡ���������Դ����·ͨ����  
  
        DatagramChannel channel = DatagramChannel.open();  
        channel.configureBlocking(false);  
        channel.socket().bind(new InetSocketAddress(8300)); 		// ͨ��������socket�󶨵�ַ  
        channel.register(sel, SelectionKey.OP_READ, new ClientData());  
  
        while (true) { 													// �������У����պͷ�������  
            if (sel.select(TIMEOUT) == 0) {  
                System.out.println("No I/O needs to be processed");  
                continue;  
            }  
  
            Iterator<SelectionKey> iter = sel.selectedKeys().iterator(); // ��ȡ�ɲ�����ѡ�������  
            while (iter.hasNext()) {  
                SelectionKey key = iter.next(); 						// ��Ϊλ����  
  
                if (key.isReadable()) { 								// �ͻ��������ݷ��͹���  
                    handleRead(key);  
                }  
  
                if (key.isValid() && key.isWritable()) { 				// ͨ���������ҿͻ�����Ҫ��Ӧ  
                    handleWrite(key);  
                }  
  
                iter.remove(); 											// �Ӽ������Ƴ�ѡ���  
            }  
  
        }  
    }  
  
    private static void handleRead(SelectionKey key) throws IOException {  
        DatagramChannel channel = (DatagramChannel) key.channel();  
        ClientData clntDat = (ClientData) key.attachment();  
        clntDat.buffer.clear();  
        clntDat.clientAddress = channel.receive(clntDat.buffer); 		// ��ȡ�ͻ��˵ĵ�ַ�����Է�����Ӧ  
        if (clntDat.clientAddress != null) {							// ���յ�����  
            key.interestOps(SelectionKey.OP_WRITE); 					// ��ע�ͻ��˶�ȡ��Ӧ  
            System.out.println("��ע�ͻ��˶�ȡ��Ӧ");
        }  
    }  
  
    private static void handleWrite(SelectionKey key) throws IOException {  
        DatagramChannel channel = (DatagramChannel) key.channel();  
        ClientData clntDat = (ClientData) key.attachment();  
        clntDat.buffer.flip(); 											// ����ʼλ�ÿ�ʼ����  
        int bytesSent = channel.send(clntDat.buffer, clntDat.clientAddress);  
        if (bytesSent != 0) {  
            key.interestOps(SelectionKey.OP_READ); 						// ��ע�ͻ��˷�������  
            System.out.println("��ע�ͻ��˷�������");
        }  
    }  
  
    public static class ClientData {  
        public SocketAddress clientAddress;  
        public ByteBuffer buffer = ByteBuffer.allocate(CAPACITY);  
    }  
	
	
}
