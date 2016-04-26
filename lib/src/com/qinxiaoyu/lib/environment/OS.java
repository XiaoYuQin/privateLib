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
	 * 获得操作系统构架
	 * @author    秦晓宇
	 * @date      2016年4月23日 上午10:50:50 
	 * @return 
	 * 			-操作系统构架
	 */
	public static String getOsArch()
	{
		Properties prop = System.getProperties();
		String arch = prop.getProperty("os.arch");
		System.out.println(arch);
		return arch;
	}
	/**
	 * 获得操作系统版本
	 * @author    秦晓宇
	 * @date      2016年4月23日 上午10:51:14 
	 * @return	
	 * 			-操作系统版本
	 */
	public static String getOsVersion()
	{
		Properties prop = System.getProperties();
		String version = prop.getProperty("os.version");
		System.out.println(version);
		return version;
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
