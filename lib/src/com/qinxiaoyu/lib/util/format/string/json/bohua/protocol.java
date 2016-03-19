package com.qinxiaoyu.lib.util.format.string.json.bohua;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.google.gson.annotations.Since;



public class protocol {

	public dev dev;
//	@Expose
	public String msg_type;
	public ArrayList<LinkedHashMap<String, String>> data;
//	@SerializedName("echo")
//	@SerializedName("test")
	public String time;
//	@Since(2.0)
	public echo echo;
	@Since(1.0)
	public check check;
		
	public protocol()
	{
		dev 	= new dev();
		data 	= new ArrayList<LinkedHashMap<String, String>>();
		echo 	= new echo();
		check 	= new check();
	}
	
	public void setDev(String dev_name,String dev_sn,String protocol_version )
	{
		this.dev.dev_name = dev_name;
		this.dev.dev_sn = dev_sn;
		this.dev.protocol_version = protocol_version;
	}
	public void setMsgtype(String msg_type)
	{
		this.msg_type = msg_type;
	}
	public void addData(LinkedHashMap<String,String> keys)
	{
		this.data.add(keys);
	}
	
	public void setTime(String time)
	{
		this.time = time;
	}
	public void setEcho(int sn_id,String state)
	{
		echo echo = new echo();
		echo.sn_id = sn_id;
		echo.state = state;
		this.echo = echo;
	}
	public void setCheck(String type,String value)
	{
		check check = new check();
		check.type = type;
		check.value = value;
		this.check = check;
	}
}
