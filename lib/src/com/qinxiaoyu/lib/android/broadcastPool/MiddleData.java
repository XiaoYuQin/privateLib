package com.qinxiaoyu.lib.android.broadcastPool;

import java.util.LinkedHashMap;
import java.util.Map;

public class MiddleData {

	public static final String action = "BOHUA_BROADCAST";
	public String destination;
	public String msgType;
	public Map<String,Object> data;
	
	
	public MiddleData()
	{
		data = new LinkedHashMap<String, Object>(); 
	}
//	public void setDestination(String destination)
//	{
//		this.destination = destination;
//	}
//	public void setMsgType(String msgType)
//	{
//		this.msgType = msgType;
//	}
	
	
	
}
