package com.qinxiaoyu.lib.module.transmit.net.udpBroadcast.EXAMPLE;

import com.qinxiaoyu.lib.module.transmit.net.udpBroadcast.UdpBroadcastReciver;

public class Reciver {
	public static void main(String[] args){
		System.out.println("reciverr");
		try {
			UdpBroadcastReciver reciver = new UdpBroadcastReciver(8300);
			for(;;){
				System.out.println(reciver.recive().toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
