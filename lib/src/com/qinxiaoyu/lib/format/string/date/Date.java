package com.qinxiaoyu.lib.format.string.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {

	/**
	 * 以"yyyy-MM-dd HH:mm:ss"格式获得系统日期
	 * */
//	public static String sysDateToString()
//	{
//		//设置日期格式
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		return df.format(new Date());
//	}
	
	public static String sysDateToString()
	{
		 Calendar rightNow = Calendar.getInstance(); 
		 SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式大小写有区别 
		 String sysDatetime = fmt.format(rightNow.getTime()); 
		 return sysDatetime;
	}
}
