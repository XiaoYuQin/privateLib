package com.qinxiaoyu.lib.module.transmit.net.udp;

import java.util.Vector;

public class UdpClientPool {

	private Vector<UdpClientData> udpClients;
	Boolean isRun = false;
	
	private class UdpClientData
	{
		String ip;
		String data;
		int port;
	}
	public void close()
	{
		isRun = false;
		while(udpClients.size()>0)
		{
			udpClients.remove(0);
		}
	}
	public UdpClientPool()
	{
		udpClients = new Vector<UdpClientData>();
		new UdpClientSendThreader().start();
		isRun = true;
	}
	public void write(String ip,int port,String input)
	{
		UdpClientData udpClientData = new  UdpClientData();
		udpClientData.ip = ip;
		udpClientData.data = input;
		udpClientData.port = port;
		udpClients.add(udpClientData);
	}
	private class UdpClientSendThreader extends Thread
	{
		@Override
		public void run()
		{
			while(isRun)
			{
				if(udpClients.size()>0)
				{
					UdpClientData d = new UdpClientData();
					d.ip = udpClients.get(0).ip;
					d.port = udpClients.get(0).port;
					d.data = udpClients.get(0).data;
					UdpClient.send(d.ip, d.port, d.data);
					udpClients.remove(0);
				}

			}
		}
	}
	
}
