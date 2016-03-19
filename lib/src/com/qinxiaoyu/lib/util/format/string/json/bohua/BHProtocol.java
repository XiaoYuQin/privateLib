package com.qinxiaoyu.lib.util.format.string.json.bohua;

import com.qinxiaoyu.lib.util.format.map.LibLinkedHashMap;

/**
 * 博华科技的Json传输协议数据结构类
 * */
public class BHProtocol {
	
	private static void debug(String obj)
	{
		System.out.println("BHProtocol : "+obj);
	}
	
	public protocol protocol;
	public BHProtocol()
	{
		protocol = new protocol();
	}
	
	/**
	 * 判断两个BHProtocol实体类是否相同
	 * @return false:不相同	， true:相同
	 * */
	public boolean equals(BHProtocol pro)
	{
		if(this == pro)
		{
			debug("this == pro");
			return true;
		}
			
		
		if(!this.protocol.dev.dev_name .equals(pro.protocol.dev.dev_name) )
		{
			debug("dev_name)");
			return false;
		}
			
		if(!this.protocol.dev.dev_sn.equals(pro.protocol.dev.dev_sn) )
		{
			debug("dev_sn");
			return false;
		}
			
		if(!this.protocol.msg_type.equals(pro.protocol.msg_type) )
		{
			debug("msg_type");
			return false;
		}
		
		if(this.protocol.data.size() != pro.protocol.data.size())
		{
			debug("data.size()");
			return false;
		}
		
		for(int i=0;i<this.protocol.data.size();i++)
		{
			if(this.protocol.data.get(i).size() == pro.protocol.data.get(i).size())
			{
				debug("data.get("+"i"+") .size 相同");
				if(LibLinkedHashMap.equal(this.protocol.data.get(i), pro.protocol.data.get(i))==false)
				{
					debug("data.get("+"i"+")  内容不相同");
					return false;
				}
				else
				{
					debug("data.get("+"i"+") 内容相同");
				}
			}
			else
			{
				debug("data.get("+"i"+") .size 不同");
				return false;
			}
		}
		
		if(this.protocol.echo.sn_id != pro.protocol.echo.sn_id)
		{
			debug("echo.sn_id");
			return false;
		}
		if(!this.protocol.echo.state.equals(pro.protocol.echo.state))
		{
			debug("echo.state");
			return false;
		}
		if(!this.protocol.check.type.equals(pro.protocol.check.type))
		{
			debug("check.type");
			return false;
		}
		if(!this.protocol.check.value.equals(pro.protocol.check.value))
		{
			debug("check.value");
			return false;
		}
		if(!this.protocol.time.equals(pro.protocol.time))
		{
			debug("time");
			return false;
		}
		
		debug("true");
		return true;
	}
	/**
	 * 判断一个BHProtocol实体类是否同一个设备
	 * @param pro : 一个设备信息实例
	 * @return : true 来自于一个设备，false 来自于不同设备
	 * */
	public boolean isOneDevice(BHProtocol pro)
	{
		if(!this.protocol.dev.dev_name .equals(pro.protocol.dev.dev_name) )
		{
			debug("dev_name)");
			return false;
		}
		if(!this.protocol.dev.dev_sn.equals(pro.protocol.dev.dev_sn) )
		{
			debug("dev_sn");
			return false;
		}
		return true;
	}


}
