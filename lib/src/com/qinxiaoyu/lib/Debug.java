package com.qinxiaoyu.lib;

import android.util.Log;

public class Debug {
	
	public static void debugx(String tag,String msg){Log.i(tag,msg);}
//	public static void debugx(String tag,String msg){System.out.println(tag+" : "+msg);}
	



	
	
	/*Android Ŀ¼�µ�debug����*/
	public static final boolean debugLibSDcard = true;
	public static final boolean debugLibBundle = true;
	/*PC Ŀ¼�µ�debug����*/
	/*Module Ŀ¼�µ�debug����*/
	public static final boolean debugBroadcastRcvPool	 		= true;
	public static final boolean debugModuleDatapool 			= true;
	public static final boolean debugTransmitNetNetPool 		= true;
	public static final boolean debugTransimtNetUdpUdpClient 	= true;
	public static final boolean debugModuleFifo 				= true;
	/*util Ŀ¼�µ�debug����*/
	public static final boolean debugFileFile = true;
	
	public static final boolean debugBundleHandler = false;
}
