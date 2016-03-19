package com.qinxiaoyu.lib.android;

import java.util.Iterator;
import java.util.Set;

import com.qinxiaoyu.lib.Debug;

import android.os.Bundle;

public class LibBundle {

	private static void debug(String s){if(Debug.debugLibBundle)	Debug.debugx("LibBundle",s);}
	
	public static void displayAll(Bundle bundle)
	{
		debug("��ʾ����bundle������");
		Set<?> keySet = bundle.keySet();		//���ؼ��ļ��� 
		Iterator<?> it = keySet.iterator(); 
		debug("ת��Ϊkeyset");
		while(it.hasNext())      				//��һ�ֵ�����ʽȡ��ֵ 
		{ 
			Object key = it.next(); 
			debug(key.toString()+"  :  "+bundle.getString(key.toString())); 				//���ݼ���ȡ��Ӧ��ֵ 
		} 
	}
	
	
}
