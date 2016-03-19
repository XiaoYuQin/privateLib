package com.qinxiaoyu.lib.module.datapool;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.qinxiaoyu.lib.Debug;
import com.qinxiaoyu.lib.module.fifo.FifoItem;
import com.qinxiaoyu.lib.util.format.map.LibLinkedHashMap;


public class DataPool {

	/*数据集合*/
	Map<String ,FifoItem> itemMaps;
	private void debug(String str)
	{
		if(Debug.debugModuleDatapool)
		{
			Debug.debugx("DataPool", str);
		}
	}
	
	
	/**
	 * 数据池的构造函数，目前只支持内存保存的方式
	 * 创建时间：2015/4/15
	 * @author : 秦晓宇
	 **/
	public DataPool()
	{
		itemMaps = new LinkedHashMap<String,FifoItem>();
		debug("init DataPool ok");
	}
	/**
	 * 为一个名称绑定一个数据集
	 * 创建时间：2015/4/15
	 * @author : 秦晓宇
	 * @param : name 数据集的名称，数据集的实体
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
			Set<?> keySet = this.itemMaps.keySet();				//返回键的集合 
			Iterator<?> it = keySet.iterator(); 
			while(it.hasNext())      							//第一种迭代方式取键值 
			{ 
				Object key = it.next(); 
				debug("item name = "+key); 						//根据键来取对应的值 
			} 
		}
	}
	public Boolean write(String name,Object data)
	{
		synchronized(this.itemMaps)
		{
			debug("向数据池中保存一条数据");
			if(LibLinkedHashMap.isHaveKey(name, this.itemMaps))
			{
				debug("向内从缓冲池中保存一条数据");
				this.itemMaps.get(name).write(data);
				return true;
			}
//			Set<?> keySet = this.itemMaps.keySet();				//返回键的集合 
//			Iterator<?> it = keySet.iterator(); 
//			while(it.hasNext())      													//第一种迭代方式取键值 
//			{ 
//				Object key = it.next(); 
//				debug("item name = "+key); 								//根据键来取对应的值 
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
//			Set<?> keySet = this.itemMaps.keySet();				//返回键的集合 
//			Iterator<?> it = keySet.iterator(); 
//			while(it.hasNext())      													//第一种迭代方式取键值 
//			{ 
//				Object key = it.next(); 
//				debug("item name = "+key); 								//根据键来取对应的值 
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
//	 * 将实体类填入到类缓冲列队中
//	 * */
//	public void setData(String name,Object object)
//	{
//			FifoItem item = new FifoItem();
//			Items.add(data);
//	}
//	/**
//	 * 根据名字获得先入的实体类
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
