package com.qinxiaoyu.lib.format.string.xml;

import com.thoughtworks.xstream.XStream;

public class Xml extends XStream{

	
	public String toXML(Object object)
	{
		Xml xstream = new Xml();
		return xstream.toXML(object);
	}
	
	
}
