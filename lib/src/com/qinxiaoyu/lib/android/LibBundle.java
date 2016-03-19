package com.qinxiaoyu.lib.android;

import java.util.Iterator;
import java.util.Set;

import com.qinxiaoyu.lib.Debug;

import android.os.Bundle;

public class LibBundle {

	private static void debug(String s){if(Debug.debugLibBundle)	Debug.debugx("LibBundle",s);}
	
	public static void displayAll(Bundle bundle)
	{
		debug("显示所有bundle的数据");
		Set<?> keySet = bundle.keySet();		//返回键的集合 
		Iterator<?> it = keySet.iterator(); 
		debug("转换为keyset");
		while(it.hasNext())      				//第一种迭代方式取键值 
		{ 
			Object key = it.next(); 
			debug(key.toString()+"  :  "+bundle.getString(key.toString())); 				//根据键来取对应的值 
		} 
	}
	
	
}
