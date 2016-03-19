package com.qinxiaoyu.lib.util.event;

import java.util.Enumeration;
import java.util.EventListener;
import java.util.Vector;

public class BaseEventSource {

	private Vector<Object> listener;
	BaseEventListener dl;
	int data;
	
	public BaseEventSource()
	{
		 listener = new Vector<Object>();
	}
    //给事件源注册监听器  
    public void addListener(Object e)
    {  
        this.listener.add(e);  
    }  
    public void notifies()
    {
           Enumeration<Object> enumx = listener.elements();
           while(enumx.hasMoreElements())
           {
                 dl = (BaseEventListener)enumx.nextElement();
                 dl.eventFunctionCallBack(new BaseEvent(this));
           }
    }
    //模拟事件触发器，当成员变量name的值发生变化时，触发事件。  
    public void setNotifies(int data)
    {  
    		this.data = data;
            notifies();          
    } 
    public int getData()
    {
    	return this.data;
    }
    
}
