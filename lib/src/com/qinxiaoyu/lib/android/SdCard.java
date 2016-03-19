package com.qinxiaoyu.lib.android;

import java.io.File;

import com.qinxiaoyu.lib.Debug;

import android.os.Environment;

public class SdCard {
	
	private static void debug(String s){if(Debug.debugLibSDcard)	Debug.debugx("SdCard",s);}
	

    /**
     * 检查卡状态,插入SD卡并且具有访问权则返回0,未插入SD卡或者访问权限不够则返回-1
     * 创建时间：2015/5/18 
     * @author :秦晓宇
     * @return int: 插入SD卡并且具有访问权则返回0，
     * 				有SD卡权限但并未插入返回-1，
     * 				没有SD卡权限返回-2
     * */
	public static int checkSDcardStatus()
	{
		try {
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			{
				debug("SD卡插入并且具有访问权限");
				return 0;
			}
			else
			{
				return -1;
			}
		}
		catch(Exception e)
		{
			debug("SD卡未插入或者访问权限不够");
			e.printStackTrace();
			return -2;
		}
	}
	
    /**
     * 获得SD卡的路径
     * 创建时间：2015/5/18 
     * @author :秦晓宇
     * @return String: SD卡的路径
     * */
	public static String getSdcardPath()
	{
		File sd = Environment.getExternalStorageDirectory();
		String file_path= sd.getPath();
		return file_path;
	}
	
}
