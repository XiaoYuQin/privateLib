package com.qinxiaoyu.lib.module.transmit.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import com.qinxiaoyu.lib.Debug;

public class UdpClient {
	
	private static final String tag = "UdpClient";
	private static void debug(String msg)
	{
		if(Debug.debugTransimtNetUdpUdpClient)
			Debug.debugx(tag, msg);
	}

	public static void send(String ip,int port,byte[] data) throws Exception
	{
		DatagramSocket socket = new DatagramSocket();  
        DatagramPacket packet = new DatagramPacket(data, data.length,InetAddress.getByName(ip), port);  
        socket.send(packet); 
        socket.close();
	}
    /**
     * 通过udp将一串数据发送至指定ip地址的指定端口
     * 创建时间：2015/2/27
     * 修改时间：2015/3/18 
     * 		1.将抛出的异常放置在了程序内部解决，不向上抛出。
     * 		2.添加了发送出去或未发送出去的返回标志
     * @author :秦晓宇
     * @param : ip 对方的ip地址，port对方的接收端口，data发送的数据
     * @return boolean: 发送成功则返回true，发送数百返回false。
     * */
	public static boolean send(String ip,int port,String data) // throws Exception
	{
		DatagramSocket socket;
		try 
		{
			debug("send");
			socket = new DatagramSocket();
			byte buffer[] = data.getBytes();
//			debug("3");
	        DatagramPacket packet = new DatagramPacket(buffer, buffer.length,InetAddress.getByName(ip),port);  
//	        debug("1");
			socket.send(packet);
//			debug("2");
	        socket.close();
	        return true;
		} 
		catch (SocketException e) 
		{
			// TODO Auto-generated catch block
			debug("SocketException");
			e.printStackTrace();
			return false;
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			debug("Exception");
			e.printStackTrace();
			return false;
		}  

	}
	
}
