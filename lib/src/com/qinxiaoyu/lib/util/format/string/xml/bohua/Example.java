package com.qinxiaoyu.lib.util.format.string.xml.bohua;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

public class Example {

	private static void debug(Object obj){System.out.println(obj);}
	public static void main(String[] argv)
	{
		debug("main");
		
//		DEV_TYPE dev_type = new DEV_TYPE();
//		dev_type.base.TYPE_NAME = "便携式多气体检测仪V1.0";
//		dev_type.base.TYPE_MODEL = "XXXX2.0";
//		dev_type.base.TYPE_ID = "520001";
//		
//		ArrayList<String> arraylist1 = new ArrayList<String>();
//		arraylist1.add("health");
//		arraylist1.add("onTime");
//		arraylist1.add("batteryVoltage");
//		arraylist1.add("powerOnTime");
//		arraylist1.add("healthSituation");
//		dev_type.health.ITEM.add(arraylist1);
//		
//		ArrayList<String> arraylist2 = new ArrayList<String>();
//		arraylist2.add("multiGasData");
//		arraylist2.add("CH4");
//		arraylist2.add("CO");
//		arraylist2.add("NO2");
//		arraylist2.add("O2");
//		arraylist2.add("temperature");
//		arraylist2.add("dateTime");
//		dev_type.business.ITEM.add(arraylist2);
//		
//		ArrayList<String> arraylist3 = new ArrayList<String>();
//		arraylist3.add("timeSync");
//		arraylist3.add("date");
//		dev_type.business.ITEM.add(arraylist2);
//		
//		XStream xstream = new XStream();
//		String str = xstream.toXML(dev_type);
//		debug(str);
		
		
		DEV_TYPE dev_type = new DEV_TYPE();
		dev_type.BASE.TYPE_NAME = "便携式多气体检测仪V1.0";
		dev_type.BASE.TYPE_MODEL = "XXXX2.0";
		dev_type.BASE.TYPE_ID = "520001";
		
		ArrayList<String> arraylist1 = new ArrayList<String>();
		arraylist1.add("health");
		arraylist1.add("onTime");
		dev_type.HEALTH.add(arraylist1);

		ArrayList<String> arraylist2 = new ArrayList<String>();
		arraylist2.add("aaaa");
		arraylist2.add("bbbb");
		dev_type.HEALTH.add(arraylist2);
		
		
		XStream xstream = new XStream();
		xstream.alias("KEY", String.class);
		xstream.alias("ITEM", List.class);
		String str = xstream.toXML(dev_type);
		debug(str);
		
		XStream xstreamx = new XStream();
		DEV_TYPE dev_type1 = new DEV_TYPE();
		dev_type1 = (DEV_TYPE) xstreamx.fromXML(str);
		debug(dev_type1.BASE.TYPE_ID);
	}
	
}
