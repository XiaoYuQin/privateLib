package com.qinxiaoyu.lib;

import android.util.Log;

public class Debug {
	
	public static void debugx(String tag,String msg){Log.i(tag,msg);}
//	public static void debugx(String tag,String msg){System.out.println(tag+" : "+msg);}
	



	
	
	/*Android 目录下的debug控制*/
	public static final boolean debugLibSDcard = true;
	public static final boolean debugLibBundle = true;
	/*PC 目录下的debug控制*/
	/*Module 目录下的debug控制*/
	public static final boolean debugBroadcastRcvPool	 		= true;
	public static final boolean debugModuleDatapool 			= true;
	public static final boolean debugTransmitNetNetPool 		= true;
	public static final boolean debugTransimtNetUdpUdpClient 	= true;
	public static final boolean debugModuleFifo 				= true;
	/*util 目录下的debug控制*/
	public static final boolean debugFileFile = true;
	
	public static final boolean debugBundleHandler = false;
}
