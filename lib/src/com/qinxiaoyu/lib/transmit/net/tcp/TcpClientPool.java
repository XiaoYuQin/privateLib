package com.qinxiaoyu.lib.transmit.net.tcp;

import java.util.Vector;

import com.qinxiaoyu.lib.event.BaseEvent;
import com.qinxiaoyu.lib.event.BaseEventSource;

public  class TcpClientPool 
{
	
	public static enum TcpClientIoctl
	{
		RECONNECT,
		DISCONNECT
	}
	
	Vector<TcpClient> tcpClientCollection;
	public TcpClientPool()
	{
		tcpClientCollection = new Vector<TcpClient>();
	}
	/*添加某个Tcp客户端*/
	public void add(String ip,int port,BaseEventSource source)
	{
		TcpClient tcpClient = new TcpClient();
		tcpClient.open(ip, port, source);
		tcpClientCollection.add(tcpClient);
	}
	/*删除某个Tcp客户端*/
	public void remove(String ip,int port)
	{
		for(int i=0;i<tcpClientCollection.size();i++)
		{
			if(tcpClientCollection.get(i).isSame(ip, port) == true)
			{
				tcpClientCollection.remove(i);
				break;
			}
		}
	}
	/*让某个Tcp客户端发送数据*/
	public void write(String ip,int port,String data)
	{
		for(TcpClient  tcpClient :tcpClientCollection)
		{
			if(tcpClient.isSame(ip, port))
			{
				tcpClient.write(data);
			}
		}
	}
	public void ioctl(String ip,int port,TcpClientIoctl ioctlCmd)
	{
		for(TcpClient  tcpClient :tcpClientCollection)
		{
			if(tcpClient.isSame(ip, port))
			{
				switch(ioctlCmd)
				{
					case RECONNECT:
						
						break;
					case DISCONNECT:
						break;
					default:
						break;	
				}
			}
		}
	}

}
