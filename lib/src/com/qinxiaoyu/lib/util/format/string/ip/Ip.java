/**
 * @author 秦晓宇
 * @time : 2015-2-12
 * */
package com.qinxiaoyu.lib.util.format.string.ip;

import java.net.InetAddress;
import java.net.UnknownHostException;



public class Ip {
	
	private static void debug(String obj)
	{
		System.out.println("lib.format.string.ip  :  "+obj);
	}
	
	
	/**
	 * 获取本地IP地址
	 * */
	public static String getLocalIp()
	{
		InetAddress addr;
		String ip = "";
		try {
			addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress();//获得本机IP
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
	
	/**
     * 把IP地址转化为int
     * @param ipAddr
     * @return int
     */
    public static byte[] ipToBytes(String ipAddr) 
    {
        byte[] ret = new byte[4];
        try 
        {
            String[] ipArr = ipAddr.split("\\.");
            ret[0] = (byte) (Integer.parseInt(ipArr[0]) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(ipArr[1]) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(ipArr[2]) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(ipArr[3]) & 0xFF);
            return ret;
        } 
        catch (Exception e) 
        {
        	debug("转换IP地址抛出错误");
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }
    
	/**
     * 把IP地址转化为String
     * @param ipAddr ：String类型的IP地址数据
     * @return String[]  : String[0]为左侧第一个数组，String[4]为右侧第一个数据
     */
    public static String[] ipToStrings(String ipAddr) 
    {
        String[] ret = new String[4];
        try 
        {
            String[] ipArr = ipAddr.split("\\.");
            ret[0] = ipArr[0];
            ret[1] = ipArr[1];
            ret[2] = ipArr[2];
            ret[3] = ipArr[3];
            return ret;
        } 
        catch (Exception e) 
        {
        	debug("转换IP地址抛出错误");
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }
    
    /**
     * 判断输入的实体类是否是一个正确的IP地址
     * 创建时间：2015/3/18
     * @author :秦晓宇
     * @param : ip String 类型的字符串地址
     * @return boolean: 检验结果，是一个IP地址的格式则返回true，不是一个IP地址的格式则返回false
     * */
    public static boolean verifyIp(Object ip)
    {
    	boolean ret = false;
    	if(!(ip instanceof String))
    	{
    		ret = false;
    	}
    	else
    	{
    		String[] ipArr = ((String) ip).split("\\.");
    		if(ipArr.length!=4)
    			ret = false;
    		else
    		{
    	        try 
    	        {
	    			int ip0 = Integer.parseInt(ipArr[0]);
	    			int ip1 = Integer.parseInt(ipArr[1]);
	    			int ip2 = Integer.parseInt(ipArr[2]);
	    			int ip3 = Integer.parseInt(ipArr[3]);
	    			if(((ip0<0)||(ip0>255))||((ip1<0)||(ip1>255))||((ip2<0)||(ip2>255))||((ip3<0)||(ip3>255)))
	    			{
	    				ret = false;
	    			}
	    			else
	    				ret = true;
    	        }
    	        catch(Exception e)
    	        {
    	        	ret = false;
    	        }
    		}
    	}
    	return ret;
    }
    /**
     * 判断输入的实体类是否是一个在规定范围内的端口
     * 创建时间：2015/3/18
     * @author :秦晓宇
     * @param : port 端口号
     * @return boolean: 检验结果，是一个port的格式则返回true，不是一个port的格式则返回false
     * */
    public static boolean verifyPort(Object port)
    {
    	if(!(port instanceof Integer))
    	{
    		return false;
    	}
    	else
    	{
    		int portInt = Integer.parseInt(port.toString());
    		if((portInt >0)&&(portInt<65535))
    			return true;
    		else
    			return false;
    	}
    }
	
}
