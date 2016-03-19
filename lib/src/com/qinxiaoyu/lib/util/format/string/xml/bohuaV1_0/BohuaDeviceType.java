package com.qinxiaoyu.lib.util.format.string.xml.bohuaV1_0;

import java.util.ArrayList;

public class BohuaDeviceType {
	
//	public Base base;
	public ArrayList<Item>  base;
	public ArrayList<Item>  healthMessage;
	public ArrayList<Item>  businessMessage;
	public ArrayList<Item>  commandMessage;
	public ArrayList<Item>  register;
	
	public BohuaDeviceType()
	{
//		base = new Base();
		base = new  ArrayList<Item>();
		healthMessage = new ArrayList<Item>();
		businessMessage = new ArrayList<Item>();
		commandMessage = new ArrayList<Item>();
		register = new ArrayList<Item>();
	}
	public void addBase(String cmd,String type,String desc,String length)
	{
		Item item = new Item();
		item.cmd = cmd;
		item.type = type;
		item.desc = desc;
		item.length= length;
		base.add(item);
	}
	public void addBase(Item item)	{base.add(item);}
	
//	public void setBase(String deviceTypeID,String deviceTypeName,String deviceTypeVersion)
//	{
//		base.setBase(deviceTypeID, deviceTypeName, deviceTypeVersion);
//	}
	public void addHealth(String cmd,String type,String desc,String length)
	{
		Item item = new Item();
		item.cmd = cmd;
		item.type = type;
		item.desc = desc;
		item.length= length;
		healthMessage.add(item);
	}
	public void addHealth(Item item)	{healthMessage.add(item);}
	
	
	public void addBusiness(String cmd,String type,String desc,String length)
	{
		Item item = new Item();
		item.cmd = cmd;
		item.type = type;
		item.desc = desc;
		item.length= length;
		businessMessage.add(item);
	}
	public void addBusiness(Item item)	{businessMessage.add(item);}
	
	public void addCommand(String cmd,String type,String desc,String length)
	{
		Item item = new Item();
		item.cmd = cmd;
		item.type = type;
		item.desc = desc;
		item.length= length;
		commandMessage.add(item);
	}
	public void addCommand(Item item)	{commandMessage.add(item);}
	
	public void addRegister(String cmd,String type,String desc,String length)
	{
		Item item = new Item();
		item.cmd = cmd;
		item.type = type;
		item.desc = desc;
		item.length= length;
		register.add(item);
	}
	public void addRegister(Item item)	{register.add(item);}
	
	/*			Base			*/
//	public class Base
//	{
//		String deviceTypeID;
//		String deviceTypeName;
//		String deviceTypeVersion;
//		
//		public void setBase(String deviceTypeID,String deviceTypeName,String deviceTypeVersion)
//		{
//			this.deviceTypeID = deviceTypeID;
//			this.deviceTypeName = deviceTypeName;
//			this.deviceTypeVersion = deviceTypeVersion;
//		}
//		public String getDeviceTypeID()
//		{
//			return this.deviceTypeID;
//		}
//		public String getDeviceTypeName()
//		{
//			return this.deviceTypeName;
//		}
//		public String getDeviceTypeVersion()
//		{
//			return this.deviceTypeVersion;
//		}
//	}
	
	/*			health			*/
	public static class Item
	{
		String cmd;
		String type;
		String desc;
		String length;
		
		public String getCmd(){return cmd;}
		public String getType(){return type;}
		public String getDesc(){return desc;}
		public String getLength(){return length;}
	}
	
	
	public void disp()
	{
		System.out.println("disp");
		for(int i=0;i<base.size();i++)
			System.out.println("base cmd = "+base.get(i).cmd+"  type = "+base.get(i).type+"  desc = "+base.get(i).desc+"  length = "+base.get(i).length);
		for(int i=0;i<healthMessage.size();i++)
			System.out.println("healthMessage cmd = "+healthMessage.get(i).cmd+"  type = "+healthMessage.get(i).type+"  desc = "+healthMessage.get(i).desc+"  length = "+healthMessage.get(i).length);
		for(int i=0;i<businessMessage.size();i++)
			System.out.println("businessMessage cmd = "+businessMessage.get(i).cmd+"  type = "+businessMessage.get(i).type+"  desc = "+businessMessage.get(i).desc+"  length = "+businessMessage.get(i).length);
		for(int i=0;i<commandMessage.size();i++)
			System.out.println("commandMessage cmd = "+commandMessage.get(i).cmd+"  type = "+commandMessage.get(i).type+"  desc = "+commandMessage.get(i).desc+"  length = "+commandMessage.get(i).length);
		for(int i=0;i<commandMessage.size();i++)
			System.out.println("register cmd = "+register.get(i).cmd+"  type = "+register.get(i).type+"  desc = "+register.get(i).desc+"  length = "+register.get(i).length);
	}
	
	
}
