package com.qinxiaoyu.lib.module.log;

import java.util.ArrayList;

import com.qinxiaoyu.lib.module.fifo.Fifo;
import com.qinxiaoyu.lib.transmit.net.udp.UdpClient;
import com.qinxiaoyu.lib.util.format.string.ip.Ip;

/**
 * 改类重定向了所有Log信息的输出方式，目前通过UDP将数据发送至某个IP地址的UDP服务器上 <hr>
 * 1. 本类改含有一个FIFO机制，用来确定缓存Log数据的内存的大小</div>
 * 2. 本类该含有几个配置类，使之能够通过至少4种方式重定向Log输出</br>
 * 
 * <dir>
 * 		<li>2.1. udp</li>
 * 		<li>2.2. tcp</li>
 * 		<li>2.3. HTTP</li>
 * 		<li>2.4. 文本</li>
 * </dir>
 * <hr>
 * <b>示例:</b>
 * 
 * <hr>
 * @author    秦晓宇
 * @date      2016年4月22日 上午11:39:35 
 */
public class Log {


	
	UdpLog udpLog;
	
	
	
	/**
	 * UDP 输出日志
	 * @author    秦晓宇
	 * @date      2016年4月23日 上午9:45:09 
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
		 * 将Log数据写入FIFO文件中
		 * @author    秦晓宇
		 * @date      2016年4月23日 上午9:53:02 
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
