package com.qinxiaoyu.lib.module.transmit.net.udp.EXAMPLE;

import com.qinxiaoyu.lib.module.transmit.net.udp.UdpServer;

public class Server {

	public static void main(String[] argv){
		System.out.println("reciverr");
		try {
			UdpServer UdpServer = new UdpServer(8300,0);
			for(;;){
				System.out.println(UdpServer.recive().toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
