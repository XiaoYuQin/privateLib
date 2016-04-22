package com.qinxiaoyu.lib.environment;

import java.util.Properties;

public class OS {

	enum SYSTEM_ENUM{
		NONE,
		WINDOWS		
	};
	
	/**
	 * 获得操作系统的名称
	 * @author    秦晓宇
	 * @date      2016年4月18日 上午9:23:19 
	 * @return	操作系统的名称
	 */
	public static String getOsName()
	{
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		System.out.println(os);
		return os;
	}
	/**
	 * 获得操作系统的类型，OS可为window，linux，android
	 * @author    秦晓宇
	 * @date      2016年4月18日 上午9:22:26 
	 * @return		系统的类型
	 */
	public static SYSTEM_ENUM getOsType()
	{
		SYSTEM_ENUM osType = SYSTEM_ENUM.NONE;
		String osString = getOsName();
		if((osString.indexOf("win")!=-1)||(osString.indexOf("Win")!=-1))
		{
			System.out.println("os type is windows");
		}
		return osType;
	}
	
}
