package com.qinxiaoyu.lib.util.date;

import java.text.SimpleDateFormat;

import com.qinxiaoyu.lib.Debug;

public class Date {

	private static String tag = "Date";
	private static void debug(String string)	{if(Debug.DateDebug == true) Debug.debugx(tag,string);}
	
	public String getSystemDateToString()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String date = simpleDateFormat.format(new java.util.Date());
		debug("getSystemForString = "+date);
		return date;
	}
	/**
	 * ���ز�����ʱ�����ϵͳʱ��
	 * */
	public String getSystemDateToStringWithoutHMS()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String date = simpleDateFormat.format(new java.util.Date());
		debug("getSystemForString = "+date);
		return date;
	}
	public String getSystemDateToStringWithoutS()
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String date = simpleDateFormat.format(new java.util.Date());
		debug("getSystemForString = "+date);
		return date;
	}
	
}
