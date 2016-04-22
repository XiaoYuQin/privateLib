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
