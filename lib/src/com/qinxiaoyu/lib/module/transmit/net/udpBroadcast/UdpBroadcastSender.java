package com.qinxiaoyu.lib.module.transmit.net.udpBroadcast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpBroadcastSender {

	private DatagramSocket socket;  
    private DatagramPacket packet;  
    private int port = 0;
    private String ip = "";
	public UdpBroadcastSender(String ip,int port) throws Exception{
        this.port = port;
        this.ip = ip;
		socket = new DatagramSocket();  
        socket.setBroadcast(true); //有没有没啥不同 
	} 
	
	public void send(byte[] data) throws Exception{
		packet = new DatagramPacket(data,data.length,InetAddress.getByName(this.ip),this.port);
		socket.send(packet);
	}
	
	public void send(String data) throws Exception{
		byte[] buffer = data.getBytes();
		packet = new DatagramPacket(buffer,buffer.length,InetAddress.getByName(this.ip),this.port);
		socket.send(packet);
	}
	
}
