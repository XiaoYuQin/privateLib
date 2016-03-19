package com.qinxiaoyu.lib.util;

import java.util.ArrayList;

import com.qinxiaoyu.lib.format.string.json.bohua.BHProtocol;

public class LibArrayList {

	private static void debug(String obj)
	{
		System.out.println("LibArrayList : "+obj);
	}
	
	
	/**
	 * ****！！未完成：：有时还有漏数据的情况
	 * 
	 * 
	 * 判断ArrayList中是否含有相同BHProtocol实体类
	 * @param obj ：比较的ArrayList
	 * @param obj_in : 用来比较的object
	 * 
	 * @return ret : false 表示链表中不存在 ，true 表示链表中已经存在
	 * */
	public static boolean isHaveObject(ArrayList<BHProtocol> obj,BHProtocol in)
	{
		System.out.println("LibArrayList.isHave"+obj.size());
		boolean ret = false;
		if(obj.size() == 0)	return false;
		for(int i=0;i<obj.size();i++)
		{
			if(obj.get(i).equals(in))
			{
				debug("已经含有这样一个类");
				ret = true;
				break;
			}
		}
		return ret;
	}
	public static boolean isHaveDevice(ArrayList<BHProtocol> obj,BHProtocol in)
	{
		if(obj.size() == 0) return false;
		for(int i=0;i<obj.size();i++)
		{
			if(obj.get(i).isOneDevice(in) == false)
			{
				debug("不含有这样一个设备");
				return false;
			}
			else
			{
				debug("已有这样一个设备");
			}
		}
		return true;
	}
	
	
	
	
	
}
