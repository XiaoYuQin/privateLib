package com.qinxiaoyu.lib.util;

import java.util.ArrayList;

import com.qinxiaoyu.lib.format.string.json.bohua.BHProtocol;

public class LibArrayList {

	private static void debug(String obj)
	{
		System.out.println("LibArrayList : "+obj);
	}
	
	
	/**
	 * ****����δ��ɣ�����ʱ����©���ݵ����
	 * 
	 * 
	 * �ж�ArrayList���Ƿ�����ͬBHProtocolʵ����
	 * @param obj ���Ƚϵ�ArrayList
	 * @param obj_in : �����Ƚϵ�object
	 * 
	 * @return ret : false ��ʾ�����в����� ��true ��ʾ�������Ѿ�����
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
				debug("�Ѿ���������һ����");
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
				debug("����������һ���豸");
				return false;
			}
			else
			{
				debug("��������һ���豸");
			}
		}
		return true;
	}
	
	
	
	
	
}
