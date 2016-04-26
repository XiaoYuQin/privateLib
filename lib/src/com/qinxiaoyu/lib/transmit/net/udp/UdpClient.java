package com.qinxiaoyu.lib.transmit.net.udp;

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
	 * 通过udp将一串数据发送至指定ip地址的指定端口<hr>
	 * @author    秦晓宇
	 * @date      2015年2月27日 
	 * @param ip 	-发送方的ip地址
	 * @param port	-对方的接收端口
	 * @param data	-发送的数据
	 * @return
	 * 			-发送成功则返回true<br>
	 * 			-发送失败返回false。
	 */
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
