package com.qinxiaoyu.lib.module.transmit.net.socket;

import com.qinxiaoyu.lib.util.event.BaseEventSource;

public abstract class SocketAbstract {
	public abstract Boolean open(String ip,int port,BaseEventSource listener);
	public abstract Boolean close();
	public abstract void write(String data);
	public abstract void write(Byte[] data);
	public abstract String read();
	public abstract void ioctl();
}
