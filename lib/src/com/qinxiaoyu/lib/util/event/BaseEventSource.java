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
    //���¼�Դע�������  
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
    //ģ���¼�������������Ա����name��ֵ�����仯ʱ�������¼���  
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
