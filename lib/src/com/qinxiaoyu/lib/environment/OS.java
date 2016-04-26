package com.qinxiaoyu.lib.environment;

import java.util.Properties;

public class OS {

	enum SYSTEM_ENUM{
		NONE,
		WINDOWS		
	};
	
	/**
	 * ��ò���ϵͳ������
	 * @author    ������
	 * @date      2016��4��18�� ����9:23:19 
	 * @return	����ϵͳ������
	 */
	public static String getOsName()
	{
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		System.out.println(os);
		return os;
	}
	/**
	 * ��ò���ϵͳ����
	 * @author    ������
	 * @date      2016��4��23�� ����10:50:50 
	 * @return 
	 * 			-����ϵͳ����
	 */
	public static String getOsArch()
	{
		Properties prop = System.getProperties();
		String arch = prop.getProperty("os.arch");
		System.out.println(arch);
		return arch;
	}
	/**
	 * ��ò���ϵͳ�汾
	 * @author    ������
	 * @date      2016��4��23�� ����10:51:14 
	 * @return	
	 * 			-����ϵͳ�汾
	 */
	public static String getOsVersion()
	{
		Properties prop = System.getProperties();
		String version = prop.getProperty("os.version");
		System.out.println(version);
		return version;
	}
	/**
	 * ��ò���ϵͳ�����ͣ�OS��Ϊwindow��linux��android
	 * @author    ������
	 * @date      2016��4��18�� ����9:22:26 
	 * @return		ϵͳ������
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
