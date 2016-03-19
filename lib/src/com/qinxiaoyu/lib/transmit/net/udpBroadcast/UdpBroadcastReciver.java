package com.qinxiaoyu.lib.transmit.net.udpBroadcast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpBroadcastReciver {
	private DatagramSocket server;  
    private DatagramPacket packet;  
    private int port = 0;
    byte[] buffer ;
    
    public UdpBroadcastReciver(int port)throws Exception{
        this.port = port;
        buffer = new byte[65535];
        server = new DatagramSocket(this.port);  
        packet = new DatagramPacket(buffer , buffer.length);  
    }
    public String recive()throws Exception{
    	this.server.receive(packet);
    	 String s = new String(packet.getData( ), 0, packet.getLength( )); 
    	 return s;
    }

}
