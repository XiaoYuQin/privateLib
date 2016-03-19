package com.qinxiaoyu.lib.module.transmit.net;

import com.qinxiaoyu.lib.Debug;
import com.qinxiaoyu.lib.module.transmit.net.tcp.TcpClientPool;
import com.qinxiaoyu.lib.module.transmit.net.udp.UdpClient;
import com.qinxiaoyu.lib.module.transmit.net.udp.UdpClientPool;
import com.qinxiaoyu.lib.module.transmit.net.udp.UpdServerPool;
import com.qinxiaoyu.lib.util.event.BaseEventSource;


/**管理各网络连接*/
public class NetPool {
	private UpdServerPool udpServerPool;
	private UdpClientPool udpClientPool;
	
	private TcpClientPool tcpClientPool;
	
	private void debug(String str)
	{
		if(Debug.debugTransmitNetNetPool)
		{
			Debug.debugx("NetPool", str);
		}
	}
	
	public static enum NET_TYPE
	{
		/**@Expand:
		 * */
		TCP_CLIENT,
		TCP_SERVER,
		UDP_CLIENT,
		UDP_SERVER,
		UDP_BROADCAST
	}
	
	public NetPool()
	{
		udpServerPool = new  UpdServerPool();
		debug("init udpServerPool ok");
		
		udpClientPool = new UdpClientPool();
		debug("init udpClientPool ok");
		
		tcpClientPool = new TcpClientPool();
		debug("init tcpClientPool ok");
		
		

	}
	public void add(NET_TYPE netType,String ip,int port,BaseEventSource source)
	{
		switch(netType)
		{
			case TCP_CLIENT:
				tcpClientPool.add(ip, port, source);
				break;
			case TCP_SERVER:
				break;
			/*UDP客户端不需要添加客户端集*/
			case UDP_CLIENT:
				break;
			case UDP_SERVER:
				debug("添加UDP服务器 端口为："+port);
				udpServerPool.add(port);
				debug("添加UDP服务器"+port+"成功");
				break;
			case UDP_BROADCAST:
				break;
			default:
				break;
		}
	}
	public UpdServerPool getUdpServerPool()
	{
		return this.udpServerPool;
	}
	
    /**
     * 将数据通过不同的网络形式向不同的ip，不同的端口发送
     * 创建时间：2015/3/18 
     * @author :秦晓宇
     * @param : netType 发送的方式 ，ip 对方的ip地址，port对方的接收端口，input发送的数据
     * @return boolean: 发送成功则返回true，发送数百返回false。
     * */
	public boolean setData(NET_TYPE netType,String ip,int port,String input)
	{
		switch(netType)
		{
			case TCP_CLIENT:
				tcpClientPool.write(ip, port, input);
				break;
			case TCP_SERVER:
				break;
			case UDP_CLIENT:
				udpClientPool.write(ip, port, input);
				break;
			case UDP_SERVER:
				break;
			case UDP_BROADCAST:
				break;
			default:
				break;
		}
		return false;
	}
	
	/**
	 * 根据网络类型，和端口获得数据
	 * 注意：有可能会返回null；所以在获得数据的时候最好判断一下是否为null
	 * */
	public String getData(NET_TYPE netType,String ip,int port)
	{
		switch(netType)
		{
			case TCP_SERVER:
				break;
			case UDP_SERVER:
					return udpServerPool.getData(port);
			default:
				break;
		}
		return null;
	}


	/**顺序从网络缓冲列队中获得一条网络数据
	 * 注意：有可能会返回null；所以在获得数据的时候最好判断一下是否为null
	 * */
	private int getNetIndex = 0;
	public String getData()
	{
		switch(getNetIndex)
		{
			/*返回一个udp数据*/
			case 0:
				return udpServerPool.getData();
			/*返回一个tcp数据*/
			case 1:
				break;
			/*返回一个http数据*/
			case 2:
				break;
			/*返回一个udp广播数据*/
			case 3:
				break;
		}
		getNetIndex++;
		if(getNetIndex>3) getNetIndex=0;
		return null;	
	}
	
    /**
     * 判断输入的网络类型是否正确
     * 创建时间：2015/3/18
     * @author :秦晓宇
     * @param : Object netType 一个实体的网络类型类
     * @return boolean: 检验结果，是一个正确的网络类型返回true，是个不支持的返回类型返回false
     * 
     * */
	public static boolean verifyNetType(Object netType)
	{
		if(!(netType instanceof String))
		{
			return false;
		}
		else
		{
			if((netType.equals("udp"))||(netType.equals("tcp"))||(netType.equals("http"))||(netType.equals("udpBroadcast")))
			{
				return true;
			}
			else
				return false;
		}
	}

}
