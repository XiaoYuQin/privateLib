package com.qinxiaoyu.lib.android;

import java.io.File;

import com.qinxiaoyu.lib.Debug;

import android.os.Environment;

public class SdCard {
	
	private static void debug(String s){if(Debug.debugLibSDcard)	Debug.debugx("SdCard",s);}
	

    /**
     * ��鿨״̬,����SD�����Ҿ��з���Ȩ�򷵻�0,δ����SD�����߷���Ȩ�޲����򷵻�-1
     * ����ʱ�䣺2015/5/18 
     * @author :������
     * @return int: ����SD�����Ҿ��з���Ȩ�򷵻�0��
     * 				��SD��Ȩ�޵���δ���뷵��-1��
     * 				û��SD��Ȩ�޷���-2
     * */
	public static int checkSDcardStatus()
	{
		try {
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
			{
				debug("SD�����벢�Ҿ��з���Ȩ��");
				return 0;
			}
			else
			{
				return -1;
			}
		}
		catch(Exception e)
		{
			debug("SD��δ������߷���Ȩ�޲���");
			e.printStackTrace();
			return -2;
		}
	}
	
    /**
     * ���SD����·��
     * ����ʱ�䣺2015/5/18 
     * @author :������
     * @return String: SD����·��
     * */
	public static String getSdcardPath()
	{
		File sd = Environment.getExternalStorageDirectory();
		String file_path= sd.getPath();
		return file_path;
	}
	
}
