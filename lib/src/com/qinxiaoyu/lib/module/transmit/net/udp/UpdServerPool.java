package com.qinxiaoyu.lib.module.transmit.net.udp;

import java.io.IOException;
import java.net.BindException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import com.qinxiaoyu.lib.Debug;
/**
 * 1.管理所有的udp服务器连接
 * 		1.能够将列入pool中的udp服务器自动建立
 * 			1.当端口被占用的时候，返回建立服务器错误的信息
 * 			2.当端口没有被占用的时候，返回建立服务器正确的信息
 * 			3.当端口在当前pool中存在时，返回当前端口中存在这个服务器的信息
 * 		2.当删除一个pool中的udp服务器时，自动断开该服务器
 * 			1.当该端口服务器在pool中时，断开该服务器，并返回断开成功的信息
 * 			2.当该端口服务器不再pool中时，返回该服务器没有被建立的信息
 * 			3.当该端口服务器在pool中，但是并没有建立连接时，返回断开成功信息，并且将该服务器信息从列表中删除
 * 2.向pool中填入输出数据列表，pool自动向外发送数据
 * 		1.当端口已经建立连接时，pool将数据从列表中发送出去
 * 		2.当端口不存在时，pool自动建立服务器，并且将数据发送出去
 * 3.pool中自动接收数据，有数据时将数据送入pool中的输入数据列表
 * 		1.接收数据后，数据列表中保存数据来源端口，数据值。
 * 			1.数据迅速被取走，数据池不会大量增加
 * 			2.没有设备取出数据池中的数据，则数据在接收慢100k时，从池先入端按包删除
 * 
 * 
 * 
 * 特性：
 * 		连接池可以为list形式的
 * 		数据池为fifo形式的
 * 		连接池为一个服务器建立一条线程
 * 
 * 
 * 使用实例:
 * 		UpdServerPool pool = new UpdServerPool();
 * 		pool.add(8400);
 *		while(true)
 *		{
 *			String data = pool.getData(8400);
 *			if(!data.equals(""))
 *			debug(data);
 *		} 
 * 
 * 
 * 
 * 
 * 
 * */
public class UpdServerPool {
	
	
	private void debug(String s)
	{
		Debug.debugx("UpdServerPool", s);
	}
	
	private enum LinkState{
		occupied,							//端口被占用
		notInPool,							//不再pool中
		alreadyInPool,					//已经在pool中
		hasNotEstablished,		//还没有建立服务器
		establishing,						//正在建立服务器
		established,						//服务器已经建立
	}
	
	public class Udp{
		private int port;
		private byte[] buffer;
		private DatagramSocket socket;
		private DatagramPacket packet;
		
		private LinkState linkState;
		private ArrayList<String> dataList;
		
		public Udp(int port) throws SocketException,BindException
		{
			this.port = port;
			dataList = new ArrayList<String>();
			this.port = port;
			buffer = new byte[2048];
			socket = new DatagramSocket(this.port); 
			packet = new DatagramPacket(buffer , buffer.length);  
		}
		public void close()
		{
			synchronized(this.socket){
				debug("socket close");
				this.socket.close();
			}
		}
		/*设置当前udp服务器的状态*/
		public void setState(LinkState state)
		{
			this.linkState = state;
		}
		/*获取当前udp服务器的状态*/
		public LinkState getState()
		{
			return this.linkState;
		}
		/*获取当前udp服务器的端口号*/
		public int getPort()
		{
			return this.port;
		}
//		/*获取整个数据表*/
//		public ArrayList<String> getDataList()
//		{
//			return this.dataList;
//		}
		/*向数据池中读出一条数据*/
		public String getData()
		{
			String ret = "";
//			debug("dataList size = "+dataList.size());
			synchronized(dataList){
				
				if(dataList.size()!=0)
				{
					ret = dataList.get(0);
					dataList.remove(0);
				}
	//			debug("ret = "+ret);
				
				if(ret == null)	
				{
					debug("null2");
					debug("dataList 长度为"+dataList.size());
				}
			}
			return ret;
		}
//		/*向数据池中输入一条数据*/
//		public void setData(String data)
//		{
//			
//		}
		public void send(String ip,int port,byte[] data) throws Exception
		{
			DatagramSocket socket = new DatagramSocket();  
	        DatagramPacket packet = new DatagramPacket(data, data.length,InetAddress.getByName(ip), port);  
	        socket.send(packet);  
	        socket.close();
		}
		public void send(String ip,int port,String data) throws Exception
		{
			DatagramSocket socket = new DatagramSocket(); 
			byte buffer[] = data.getBytes();  
	        DatagramPacket packet = new DatagramPacket(buffer, buffer.length,InetAddress.getByName(ip),port);  
	        socket.send(packet);  
	        socket.close();
		}
		

		
		
	}
	
	public class Recive extends Thread{
		
		Udp udp;
		boolean close = true;
//		int rcvDataLen = 0;
		public Recive(Udp udp)
		{
			debug("recive thread port = "+udp.port);
			this.udp = udp;
		}
		@Override
		public void run()
		{
			while(this.close)
			{
				try 
				{
					udp.socket.receive(udp.packet);
			   	 	String data = new String(udp.packet.getData( ), 0, udp.packet.getLength( )); 
//					debug("data = "+data);
					debug("recive thread data = "+data);
					synchronized(udp.dataList)
					{
						if(udp.dataList.size()>500)
						{
							udp.dataList.remove(0);
							debug("去掉第一条");
						}
			   	 		udp.dataList.add(data);
					}
				}
				catch (SocketException e)
				{
					debug("在recive时关闭socket导致抛出异常");
//					e.printStackTrace();
				}
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		public void close()
		{
			debug("recive close");
			this.close = false;
			udp.socket.close();
		}
	}
	
	
	/*****************************************************************************************/
	
//	private static final int poolThreadCount = 3;
	private ArrayList<Udp> pool;
	private ArrayList<Recive> rcvThreadPool;
//	private ExecutorService poolThread;
	
	
	public UpdServerPool()
	{
		debug("UpdServerPool");
		pool = new ArrayList<Udp>();
		rcvThreadPool = new ArrayList<Recive>();
//		poolThread = Executors.newFixedThreadPool(poolThreadCount);
	}
	public LinkState add(int port){
		
		LinkState linkState = LinkState.notInPool;
		Udp udp;
		synchronized(pool)
		{
			for(int i=0;i<pool.size();i++)
			{
				if(pool.get(i).getPort()==port)
				{
					debug(port+"已经存在，不用再次添加");
					return linkState = LinkState.alreadyInPool;
				}
			}
			try 
			{
				udp = new Udp(port);
				pool.add(udp);
				udp.setState(LinkState.alreadyInPool);
				Recive recive = new Recive(udp);
				rcvThreadPool.add(recive);
//				poolThread.execute(recive);
				recive.start();
				debug("添加"+port+"端口成功");
			} 
			catch (BindException e)
			{
				debug(port+"端口已经被其他地方占用");
//				e.printStackTrace();
				linkState = LinkState.occupied;
			}
			catch (SocketException e) 
			{
				debug("pool中抛出socket异常");
//				e.printStackTrace();
			}
		}
		return linkState;
	}
	public void removeAll()
	{
		synchronized(pool)
		{
			synchronized(rcvThreadPool)
			{
				for(int i=this.rcvThreadPool.size()-1;i>=0;i--)
				{
					debug("rcvThreadPool.size() = "+rcvThreadPool.size());
					debug("pool.size() = "+pool.size());
					debug("停止线程 "+i+"  port = "+this.pool.get(i).getPort());
					this.rcvThreadPool.get(i).close();
					this.pool.get(i).close();
					debug("从列表中移除该udp服务器");
					this.rcvThreadPool.remove(i);
					this.pool.remove(i);
				}
			}
		}
	}
	public void remove(int port)
	{
		synchronized(pool)
		{
			synchronized(rcvThreadPool)
			{
				for(int i=this.rcvThreadPool.size()-1;i>=0;i--)
				{
					debug("rcvThreadPool.size() = "+rcvThreadPool.size());
					debug("pool.size() = "+pool.size());
					if(this.pool.get(i).getPort() == port)
					{
						debug("停止线程 "+i+"  port = "+this.pool.get(i).getPort());
						this.rcvThreadPool.get(i).close();
						this.pool.get(i).close();
						debug("从列表中移除该udp服务器");
						this.rcvThreadPool.remove(i);
						this.pool.remove(i);
					}
				}
			}
		}
	}
	public String getData(int port)
	{
		String ret = "";
		synchronized(pool)
		{
			for(int i=0;i<pool.size();i++)
			{
				if(pool.get(i).getPort() == port)
				{
					ret  = pool.get(i).getData();
					if(ret == null)	
						debug("null1");
				}
			}
		}
		return ret;
	}
	/**
	 * 获取网络数据
	 * 创建时间：2015/3/17
	 * */
	public String getData()// throws Exception
	{
		String ret = null;
		synchronized(pool)
		{
//			debug("获取数据");
			if(pool.size()>0)
			{
				ret = pool.get(0).getData();
			}
//			else
//			{
//				throw new Exception("udp pool's item is 0");
//			}
		}
		return ret;
	}
}
