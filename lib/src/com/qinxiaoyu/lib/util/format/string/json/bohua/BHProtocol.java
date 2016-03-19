package com.qinxiaoyu.lib.util.format.string.json.bohua;

import com.qinxiaoyu.lib.util.format.map.LibLinkedHashMap;

/**
 * �����Ƽ���Json����Э�����ݽṹ��
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
	 * �ж�����BHProtocolʵ�����Ƿ���ͬ
	 * @return false:����ͬ	�� true:��ͬ
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
				debug("data.get("+"i"+") .size ��ͬ");
				if(LibLinkedHashMap.equal(this.protocol.data.get(i), pro.protocol.data.get(i))==false)
				{
					debug("data.get("+"i"+")  ���ݲ���ͬ");
					return false;
				}
				else
				{
					debug("data.get("+"i"+") ������ͬ");
				}
			}
			else
			{
				debug("data.get("+"i"+") .size ��ͬ");
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
	 * �ж�һ��BHProtocolʵ�����Ƿ�ͬһ���豸
	 * @param pro : һ���豸��Ϣʵ��
	 * @return : true ������һ���豸��false �����ڲ�ͬ�豸
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
