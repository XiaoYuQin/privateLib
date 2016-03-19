/**
 * @author ������
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
	 * ��ȡ����IP��ַ
	 * */
	public static String getLocalIp()
	{
		InetAddress addr;
		String ip = "";
		try {
			addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress();//��ñ���IP
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ip;
	}
	
	/**
     * ��IP��ַת��Ϊint
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
        	debug("ת��IP��ַ�׳�����");
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }
    
	/**
     * ��IP��ַת��ΪString
     * @param ipAddr ��String���͵�IP��ַ����
     * @return String[]  : String[0]Ϊ����һ�����飬String[4]Ϊ�Ҳ��һ������
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
        	debug("ת��IP��ַ�׳�����");
            throw new IllegalArgumentException(ipAddr + " is invalid IP");
        }
    }
    
    /**
     * �ж������ʵ�����Ƿ���һ����ȷ��IP��ַ
     * ����ʱ�䣺2015/3/18
     * @author :������
     * @param : ip String ���͵��ַ�����ַ
     * @return boolean: ����������һ��IP��ַ�ĸ�ʽ�򷵻�true������һ��IP��ַ�ĸ�ʽ�򷵻�false
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
     * �ж������ʵ�����Ƿ���һ���ڹ涨��Χ�ڵĶ˿�
     * ����ʱ�䣺2015/3/18
     * @author :������
     * @param : port �˿ں�
     * @return boolean: ����������һ��port�ĸ�ʽ�򷵻�true������һ��port�ĸ�ʽ�򷵻�false
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
