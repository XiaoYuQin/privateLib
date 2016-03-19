package com.qinxiaoyu.lib.module.transmit.net.tcp.example;
//package com.qinxiaoyu.lib.transmit.net.tcp.example;
//
//import com.qinxiaoyu.lib.Debug;
//import com.qinxiaoyu.lib.event.BaseEvent;
//import com.qinxiaoyu.lib.event.BaseEventListener;
//import com.qinxiaoyu.lib.event.BaseEventSource;
//import com.qinxiaoyu.lib.format.string.ip.Ip;
//import com.qinxiaoyu.lib.transmit.net.tcp.TcpClient;
//import com.qinxiaoyu.lib.transmit.net.tcp.TcpClientListener;
//import com.qinxiaoyu.lib.transmit.net.tcp.TcpClientSource;
//
//public class Example {
//	private static void debug(String str)
//	{
//		Debug.debugx("tcp", str);
//	}
//	
//	
//	public static void main(String[] argvs)
//	{
//		debug("main");
//		TcpClient tcpClient = new TcpClient();
//		TcpClientListener tcpClientListener = new TcpClientListener();
//		TcpClientSource tcpClientSource = new TcpClientSource(); 
//		tcpClientSource.addListener(tcpClientListener);
//		tcpClient.open(Ip.getLocalIp(), 2000, tcpClientSource);
//	}
//}
