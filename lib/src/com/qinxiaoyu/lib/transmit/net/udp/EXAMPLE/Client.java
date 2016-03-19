package com.qinxiaoyu.lib.transmit.net.udp.EXAMPLE;

import java.net.InetAddress;

import com.qinxiaoyu.lib.transmit.net.udp.UdpClient;

public class Client {
	
	public static void main(String[] argv){
		System.out.println("sender");
		try {
			int count = 0;
			for(;;){
				Thread.sleep(500);
				InetAddress addr = InetAddress.getLocalHost();
				String ip=addr.getHostAddress().toString();//获得本机IP
				UdpClient.send(ip, 8400, "1234");
				System.out.println(count);
				count ++;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
