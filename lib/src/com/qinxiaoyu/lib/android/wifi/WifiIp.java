package com.qinxiaoyu.lib.android.wifi;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class WifiIp {

	public static String getIp(Context context)
	{
	     WifiManager wm=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
	     //���Wifi״̬  
	     if(!wm.isWifiEnabled())
	         wm.setWifiEnabled(true);
	     WifiInfo wi=wm.getConnectionInfo();
	     //��ȡ32λ����IP��ַ  
	     int ipAdd=wi.getIpAddress();
	     //�����͵�ַת���ɡ�*.*.*.*����ַ  
	     String ip=intToIp(ipAdd);
	     return ip;
	 }
	 private static String intToIp(int i) {
	     return (i & 0xFF ) + "." +
	     ((i >> 8 ) & 0xFF) + "." +
	     ((i >> 16 ) & 0xFF) + "." +
	     ( i >> 24 & 0xFF) ;
	 } 
	 
	 public static boolean isWifiConnected(Context context)
	 {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(wifiNetworkInfo.isConnected())
        {
            return true ;
        }
        return false ;
	 }
}
