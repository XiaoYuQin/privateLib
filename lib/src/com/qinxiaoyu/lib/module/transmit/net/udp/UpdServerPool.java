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
 * 1.�������е�udp����������
 * 		1.�ܹ�������pool�е�udp�������Զ�����
 * 			1.���˿ڱ�ռ�õ�ʱ�򣬷��ؽ����������������Ϣ
 * 			2.���˿�û�б�ռ�õ�ʱ�򣬷��ؽ�����������ȷ����Ϣ
 * 			3.���˿��ڵ�ǰpool�д���ʱ�����ص�ǰ�˿��д����������������Ϣ
 * 		2.��ɾ��һ��pool�е�udp������ʱ���Զ��Ͽ��÷�����
 * 			1.���ö˿ڷ�������pool��ʱ���Ͽ��÷������������ضϿ��ɹ�����Ϣ
 * 			2.���ö˿ڷ���������pool��ʱ�����ظ÷�����û�б���������Ϣ
 * 			3.���ö˿ڷ�������pool�У����ǲ�û�н�������ʱ�����ضϿ��ɹ���Ϣ�����ҽ��÷�������Ϣ���б���ɾ��
 * 2.��pool��������������б�pool�Զ����ⷢ������
 * 		1.���˿��Ѿ���������ʱ��pool�����ݴ��б��з��ͳ�ȥ
 * 		2.���˿ڲ�����ʱ��pool�Զ����������������ҽ����ݷ��ͳ�ȥ
 * 3.pool���Զ��������ݣ�������ʱ����������pool�е����������б�
 * 		1.�������ݺ������б��б���������Դ�˿ڣ�����ֵ��
 * 			1.����Ѹ�ٱ�ȡ�ߣ����ݳز����������
 * 			2.û���豸ȡ�����ݳ��е����ݣ��������ڽ�����100kʱ���ӳ�����˰���ɾ��
 * 
 * 
 * 
 * ���ԣ�
 * 		���ӳؿ���Ϊlist��ʽ��
 * 		���ݳ�Ϊfifo��ʽ��
 * 		���ӳ�Ϊһ������������һ���߳�
 * 
 * 
 * ʹ��ʵ��:
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
		occupied,							//�˿ڱ�ռ��
		notInPool,							//����pool��
		alreadyInPool,					//�Ѿ���pool��
		hasNotEstablished,		//��û�н���������
		establishing,						//���ڽ���������
		established,						//�������Ѿ�����
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
		/*���õ�ǰudp��������״̬*/
		public void setState(LinkState state)
		{
			this.linkState = state;
		}
		/*��ȡ��ǰudp��������״̬*/
		public LinkState getState()
		{
			return this.linkState;
		}
		/*��ȡ��ǰudp�������Ķ˿ں�*/
		public int getPort()
		{
			return this.port;
		}
//		/*��ȡ�������ݱ�*/
//		public ArrayList<String> getDataList()
//		{
//			return this.dataList;
//		}
		/*�����ݳ��ж���һ������*/
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
					debug("dataList ����Ϊ"+dataList.size());
				}
			}
			return ret;
		}
//		/*�����ݳ�������һ������*/
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
							debug("ȥ����һ��");
						}
			   	 		udp.dataList.add(data);
					}
				}
				catch (SocketException e)
				{
					debug("��reciveʱ�ر�socket�����׳��쳣");
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
					debug(port+"�Ѿ����ڣ������ٴ����");
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
				debug("���"+port+"�˿ڳɹ�");
			} 
			catch (BindException e)
			{
				debug(port+"�˿��Ѿ��������ط�ռ��");
//				e.printStackTrace();
				linkState = LinkState.occupied;
			}
			catch (SocketException e) 
			{
				debug("pool���׳�socket�쳣");
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
					debug("ֹͣ�߳� "+i+"  port = "+this.pool.get(i).getPort());
					this.rcvThreadPool.get(i).close();
					this.pool.get(i).close();
					debug("���б����Ƴ���udp������");
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
						debug("ֹͣ�߳� "+i+"  port = "+this.pool.get(i).getPort());
						this.rcvThreadPool.get(i).close();
						this.pool.get(i).close();
						debug("���б����Ƴ���udp������");
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
	 * ��ȡ��������
	 * ����ʱ�䣺2015/3/17
	 * */
	public String getData()// throws Exception
	{
		String ret = null;
		synchronized(pool)
		{
//			debug("��ȡ����");
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
