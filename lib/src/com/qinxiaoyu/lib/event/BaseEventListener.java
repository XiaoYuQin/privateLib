package com.qinxiaoyu.lib.event;

import java.util.EventListener;

public interface BaseEventListener extends EventListener{

	public abstract void eventFunctionCallBack(BaseEvent e);
}
