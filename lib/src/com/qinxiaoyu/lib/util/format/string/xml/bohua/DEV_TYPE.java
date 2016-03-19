package com.qinxiaoyu.lib.util.format.string.xml.bohua;

import java.util.ArrayList;


public class DEV_TYPE {

	public base BASE;
	ArrayList<ArrayList<String>> HEALTH;
//	public health HEALTH;
	public DEV_TYPE()
	{
		BASE = new base();
		HEALTH = new ArrayList<ArrayList<String>>();
//		HEALTH = new health();
		
	}
	public static class base{
		String TYPE_NAME;
		String TYPE_MODEL;
		String TYPE_ID;
		
		
		public void setBaseName(String name){TYPE_NAME = name;}
		public void setBaseModel(String model){TYPE_MODEL = model;}
		public void setBaseId(String id){TYPE_ID = id;}
	}
//	public static class health
//	{
//		ArrayList<ArrayList<String>> ITEM;
//		public health()
//		{
//			ITEM = new ArrayList<ArrayList<String>>();
//		}
//	}
	
	
//	public BASE base;
//	public HEALTH health;
//	public BUSINESS business;
//	public COMMAND command;
//	public DEV_TYPE()
//	{
//		base = new BASE();
//		health = new HEALTH();
//		business = new BUSINESS();
//		command = new COMMAND();
//	}
//	
//
//	public class BASE{
//		String TYPE_NAME;
//		String TYPE_MODEL;
//		String TYPE_ID;
//	}
//	public class HEALTH{
//		ArrayList<ArrayList<String>> ITEM;
//		
//		public HEALTH()
//		{
//			ITEM = new ArrayList<ArrayList<String>>();
//		}
//	}
//	
//	public class BUSINESS{
//		ArrayList<ArrayList<String>> ITEM;
//		public BUSINESS()
//		{
//			ITEM = new ArrayList<ArrayList<String>>();
//		}
//	}
//	
//	public class COMMAND{
//		ArrayList<ArrayList<String>> ITEM;
//		public COMMAND()
//		{
//			ITEM = new ArrayList<ArrayList<String>>();
//		}
//	}
	
}
