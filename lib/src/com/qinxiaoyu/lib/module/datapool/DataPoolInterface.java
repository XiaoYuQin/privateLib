package com.qinxiaoyu.lib.module.datapool;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataPoolInterface {

	private Map<String,Object> items;
	
	public static enum StoreType{
		MEMORY_STORE,
		DATABASE_STORE
	}
	public class Item
	{
		private String name;
		private Object obj;
		private ArrayList<Object> data;
		/*������*/
		public Item()
		{
			data = new ArrayList<Object>();
		}
		/*�������ݼ�*/
		 public Boolean setItem(String name ,Object obj)
		 {
			 if(name.equals("")||(name==null))			return false;
			 if(obj==null)													return false;
			 
			 this.name = name;
			 this.obj = obj;
			 return true;
		 }
		 /*������д��������ݼ������ݳ���*/
		 public Boolean setData(Object data)
		 {
			 if(data.getClass()!=this.obj.getClass())	return false;
			 this.data.add(data);
			 return true;
		 }
		 /*��ȡ���ݼ��е�����,��������ĿС��*/
		 public Object getData()
		 {
			 if(this.data.size()>0)
			 {
				 Object o = new Object();
				 o = this.data.get(0);
				 this.data.remove(0);
				 return o;
			 }
			 return null;
		 }
	}
	public DataPoolInterface()
	{
		items = new LinkedHashMap<String,Object>();
	}
	public Boolean addItem(StoreType stroeType,String name,Object obj)
	{
		ArrayList<Object> list = new ArrayList<Object>();
		obj.getClass();
		items.put(name, list);
		return null;	
	}
	public Boolean removeItem(String name)
	{
		
		return null;
	}
	public Boolean setData(String name ,Object obj)
	{
		
		return null;
	}
	public Object getData(String name)
	{
		
		return name;
	}
}
