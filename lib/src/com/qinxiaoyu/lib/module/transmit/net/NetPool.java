package com.qinxiaoyu.lib.module.transmit.net;

import com.qinxiaoyu.lib.Debug;
import com.qinxiaoyu.lib.module.transmit.net.tcp.TcpClientPool;
import com.qinxiaoyu.lib.module.transmit.net.udp.UdpClient;
import com.qinxiaoyu.lib.module.transmit.net.udp.UdpClientPool;
import com.qinxiaoyu.lib.module.transmit.net.udp.UpdServerPool;
import com.qinxiaoyu.lib.util.event.BaseEventSource;


/**�������������*/
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
			/*UDP�ͻ��˲���Ҫ��ӿͻ��˼�*/
			case UDP_CLIENT:
				break;
			case UDP_SERVER:
				debug("���UDP������ �˿�Ϊ��"+port);
				udpServerPool.add(port);
				debug("���UDP������"+port+"�ɹ�");
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
     * ������ͨ����ͬ��������ʽ��ͬ��ip����ͬ�Ķ˿ڷ���
     * ����ʱ�䣺2015/3/18 
     * @author :������
     * @param : netType ���͵ķ�ʽ ��ip �Է���ip��ַ��port�Է��Ľ��ն˿ڣ�input���͵�����
     * @return boolean: ���ͳɹ��򷵻�true���������ٷ���false��
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
	 * �����������ͣ��Ͷ˿ڻ������
	 * ע�⣺�п��ܻ᷵��null�������ڻ�����ݵ�ʱ������ж�һ���Ƿ�Ϊnull
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


	/**˳������绺���ж��л��һ����������
	 * ע�⣺�п��ܻ᷵��null�������ڻ�����ݵ�ʱ������ж�һ���Ƿ�Ϊnull
	 * */
	private int getNetIndex = 0;
	public String getData()
	{
		switch(getNetIndex)
		{
			/*����һ��udp����*/
			case 0:
				return udpServerPool.getData();
			/*����һ��tcp����*/
			case 1:
				break;
			/*����һ��http����*/
			case 2:
				break;
			/*����һ��udp�㲥����*/
			case 3:
				break;
		}
		getNetIndex++;
		if(getNetIndex>3) getNetIndex=0;
		return null;	
	}
	
    /**
     * �ж���������������Ƿ���ȷ
     * ����ʱ�䣺2015/3/18
     * @author :������
     * @param : Object netType һ��ʵ�������������
     * @return boolean: ����������һ����ȷ���������ͷ���true���Ǹ���֧�ֵķ������ͷ���false
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
