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
     * ͨ��udp��һ�����ݷ�����ָ��ip��ַ��ָ���˿�
     * ����ʱ�䣺2015/2/27
     * �޸�ʱ�䣺2015/3/18 
     * 		1.���׳����쳣�������˳����ڲ�������������׳���
     * 		2.����˷��ͳ�ȥ��δ���ͳ�ȥ�ķ��ر�־
     * @author :������
     * @param : ip �Է���ip��ַ��port�Է��Ľ��ն˿ڣ�data���͵�����
     * @return boolean: ���ͳɹ��򷵻�true���������ٷ���false��
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
