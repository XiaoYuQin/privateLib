package com.qinxiaoyu.lib.module.datapool;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.qinxiaoyu.lib.Debug;
import com.qinxiaoyu.lib.module.fifo.FifoItem;
import com.qinxiaoyu.lib.util.format.map.LibLinkedHashMap;


public class DataPool {

	/*���ݼ���*/
	Map<String ,FifoItem> itemMaps;
	private void debug(String str)
	{
		if(Debug.debugModuleDatapool)
		{
			Debug.debugx("DataPool", str);
		}
	}
	
	
	/**
	 * ���ݳصĹ��캯����Ŀǰֻ֧���ڴ汣��ķ�ʽ
	 * ����ʱ�䣺2015/4/15
	 * @author : ������
	 **/
	public DataPool()
	{
		itemMaps = new LinkedHashMap<String,FifoItem>();
		debug("init DataPool ok");
	}
	/**
	 * Ϊһ�����ư�һ�����ݼ�
	 * ����ʱ�䣺2015/4/15
	 * @author : ������
	 * @param : name ���ݼ������ƣ����ݼ���ʵ��
	 **/
	public void creat(String name)
	{
		synchronized(this.itemMaps)
		{
			FifoItem f = new FifoItem();
			this.itemMaps.put(name, f);
		}
	}
	public void destroy(String name)
	{
		synchronized(this.itemMaps)
		{
			this.itemMaps.remove(name);
		}
	}
	public void display()
	{
		synchronized(this.itemMaps)
		{
			Set<?> keySet = this.itemMaps.keySet();				//���ؼ��ļ��� 
			Iterator<?> it = keySet.iterator(); 
			while(it.hasNext())      							//��һ�ֵ�����ʽȡ��ֵ 
			{ 
				Object key = it.next(); 
				debug("item name = "+key); 						//���ݼ���ȡ��Ӧ��ֵ 
			} 
		}
	}
	public Boolean write(String name,Object data)
	{
		synchronized(this.itemMaps)
		{
			debug("�����ݳ��б���һ������");
			if(LibLinkedHashMap.isHaveKey(name, this.itemMaps))
			{
				debug("���ڴӻ�����б���һ������");
				this.itemMaps.get(name).write(data);
				return true;
			}
//			Set<?> keySet = this.itemMaps.keySet();				//���ؼ��ļ��� 
//			Iterator<?> it = keySet.iterator(); 
//			while(it.hasNext())      													//��һ�ֵ�����ʽȡ��ֵ 
//			{ 
//				Object key = it.next(); 
//				debug("item name = "+key); 								//���ݼ���ȡ��Ӧ��ֵ 
//				if(key.equals(name))
//				{
//					this.itemMaps.get(name).write(data);
//					return true;
//				}
//			} 
			return false;
		}
	}
	public Object read(String name)
	{
		synchronized(this.itemMaps)
		{
			if(LibLinkedHashMap.isHaveKey(name, this.itemMaps))
			{	
				return this.itemMaps.get(name).read();
			}
			
//			Object obj = new Object();
//			Set<?> keySet = this.itemMaps.keySet();				//���ؼ��ļ��� 
//			Iterator<?> it = keySet.iterator(); 
//			while(it.hasNext())      													//��һ�ֵ�����ʽȡ��ֵ 
//			{ 
//				Object key = it.next(); 
//				debug("item name = "+key); 								//���ݼ���ȡ��Ӧ��ֵ 
//				if(key.equals(name))
//				{
//					obj = this.itemMaps.get(name).read();
//					return obj;
//				}
//			} 
			return null;
		}
	}
	
//	/**
//	 * ��ʵ�������뵽�໺���ж���
//	 * */
//	public void setData(String name,Object object)
//	{
//			FifoItem item = new FifoItem();
//			Items.add(data);
//	}
//	/**
//	 * �������ֻ�������ʵ����
//	 * */
//	public Object getData(String name)
//	{
//		Object object = new Object();
//		synchronized(dataList)
//		{
//			for(int i=0;i<dataList.size();i++)
//			{
//				if(dataList.get(i).getName().equals(name))
//				{
//					object = dataList.get(i).getObject();
//					dataList.remove(i);
//					break;
//				}
//			}
//		}
//		return object;
//	}
	
//	private class Data
//	{
//		private String name;
//		private Object object;
//		private String date;
//		
//		public Data(String name,Object object)
//		{
//			object = new Object();
//			this.name = name;
//			this.date = Date.sysDateToString();
//		}
//		
//		public String getName()
//		{
//			return this.name;
//		}
//		public String getDate()
//		{
//			return this.date;
//		}
//		public Object getObject()
//		{
//			return this.object;
//		}
//	}
}
