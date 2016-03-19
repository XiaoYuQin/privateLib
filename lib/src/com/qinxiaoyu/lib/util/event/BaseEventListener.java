package com.qinxiaoyu.lib.util.event;

import java.util.EventListener;

public interface BaseEventListener extends EventListener{

	public abstract void eventFunctionCallBack(BaseEvent e);
}
