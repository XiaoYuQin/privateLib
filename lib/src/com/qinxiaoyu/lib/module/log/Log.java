package com.qinxiaoyu.lib.module.log;

import java.util.ArrayList;

import com.qinxiaoyu.lib.module.fifo.Fifo;
import com.qinxiaoyu.lib.transmit.net.udp.UdpClient;
import com.qinxiaoyu.lib.util.format.string.ip.Ip;

/**
 * �����ض���������Log��Ϣ�������ʽ��Ŀǰͨ��UDP�����ݷ�����ĳ��IP��ַ��UDP�������� <hr>
 * 1. ����ĺ���һ��FIFO���ƣ�����ȷ������Log���ݵ��ڴ�Ĵ�С</div>
 * 2. ����ú��м��������࣬ʹ֮�ܹ�ͨ������4�ַ�ʽ�ض���Log���</br>
 * 
 * <dir>
 * 		<li>2.1. udp</li>
 * 		<li>2.2. tcp</li>
 * 		<li>2.3. HTTP</li>
 * 		<li>2.4. �ı�</li>
 * </dir>
 * <hr>
 * <b>ʾ��:</b>
 * 
 * <hr>
 * @author    ������
 * @date      2016��4��22�� ����11:39:35 
 */
public class Log {


	
	UdpLog udpLog;
	
	
	
	/**
	 * UDP �����־
	 * @author    ������
	 * @date      2016��4��23�� ����9:45:09 
	 */
	public class UdpLog extends Thread
	{
		private String ip;
		private int port;
		private boolean isConfig = false;
				
		Fifo logData;
		
		UdpLog(String ip,int port,boolean isConfig)
		{
			this.ip = ip;
			this.port = port;
			this.isConfig = isConfig;
			logData = new Fifo();
			logData.setParam(500, Fifo.FIFO_STRATEGY.TAIL_DROP);
		}
		
		public void run()
		{
			while(this.isConfig)
			{
				synchronized(this.logData)
				{
					String logString = (String) logData.read();	
					if(logString != null)
					{
						System.out.println(logString);
						UdpClient.send(ip, port, logString);
					}		
				}						
			}
		}
		/**
		 * ��Log����д��FIFO�ļ���
		 * @author    ������
		 * @date      2016��4��23�� ����9:53:02 
		 * @param data
		 */
		public void setLogData(String data)
		{
			if(this.isConfig)
			{
				synchronized(this.logData)
				{
					logData.write(data);	
				}				
			}
		}
		
		public boolean getIsConfig() {
			return isConfig;
		}

		public int getPort() {
			return port;
		}

		public String getIp() {
			return ip;
		}

		
	}
	
	public Log() 
	{
		udpLog = new UdpLog("192.168.0.101",8000,true); 
		udpLog.start();
	}
	public void setLogData(String str)
	{
		udpLog.setLogData(str);
	}
	
	
	
	
}
