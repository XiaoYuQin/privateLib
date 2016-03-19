package com.qinxiaoyu.lib.module.plugin;

import java.util.ArrayList;




/**
 * 要素
 * 		1.apk路径
 * 		2.类名
 * 		3.方法名（方法全部由）
 * 		类继承自BuohuaPlugin，复写其中的方法。
 * 		
 * 
 * 
 * 
 * */
public class PlugIn {

	
	private String className;
	private ArrayList<Method> methods;
	private String plugInPath;
	
	
	public PlugIn(String name,String path)
	{
		methods = new ArrayList<Method>();
		this.className = name;
		this.plugInPath = path;
	}
	public void addMethod(Method method)
	{
		methods.add(method);
	}
	
	public class Method{
		private String methodName = null;
		private ArrayList<Object>	params;
		
		private Method()
		{
			params = new ArrayList<Object>();
		}
		public void setName(String name)
		{
			this.methodName = name;
		}
		public String getName()
		{
			return this.methodName;
		}
		public void addParam(Object par)
		{
			params.add(par);
		}
		public void delParam(int index)
		{
			params.remove(index);
		}
	}
	
	
}
