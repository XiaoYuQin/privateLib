package com.qinxiaoyu.lib.transmit.net.udpBroadcast.EXAMPLE;

import com.qinxiaoyu.lib.transmit.net.udpBroadcast.UdpBroadcastSender;

public class Sender {

	public static void main(String[] argv){
		System.out.println("sender");
		try {
			UdpBroadcastSender sender = new UdpBroadcastSender("255.255.255.255",8300);
			for(;;){
				Thread.sleep(1000);
				byte[] data={'1','2','3','4'};  
				sender.send(data);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
