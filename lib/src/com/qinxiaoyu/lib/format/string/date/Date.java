package com.qinxiaoyu.lib.format.string.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Date {

	/**
	 * ��"yyyy-MM-dd HH:mm:ss"��ʽ���ϵͳ����
	 * */
//	public static String sysDateToString()
//	{
//		//�������ڸ�ʽ
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		return df.format(new Date());
//	}
	
	public static String sysDateToString()
	{
		 Calendar rightNow = Calendar.getInstance(); 
		 SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//��ʽ��Сд������ 
		 String sysDatetime = fmt.format(rightNow.getTime()); 
		 return sysDatetime;
	}
}
