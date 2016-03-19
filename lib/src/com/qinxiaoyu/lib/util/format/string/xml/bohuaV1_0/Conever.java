package com.qinxiaoyu.lib.util.format.string.xml.bohuaV1_0;

import java.util.Iterator;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.qinxiaoyu.lib.Debug;
import com.qinxiaoyu.lib.util.file.LibFile;

public class Conever {

	private static void debug(String s){Debug.debugx("Conever", s);}
	
	public static BohuaDeviceType Conever()
	{
		String text = LibFile.read("/sdcard/1.txt",LibFile.CodeType.GB2312);
		debug(text);
		BohuaDeviceType deviceType = new BohuaDeviceType();
         try 
         {
			Document document = DocumentHelper.parseText(text);
			Element root = document.getRootElement();
			debug(root.getName());
			for(Iterator it=root.elementIterator();it.hasNext();)
			{
			        Element element = (Element) it.next();
			        String name0 = element.getName();
			        debug("0  "+name0);
			        for(Iterator it1=element.elementIterator();it1.hasNext();)
			        {
				        Element element1 = (Element) it1.next();
				        String name1 = element1.getName();
				        debug("1   "+name1);
				        for(Iterator it2=element1.elementIterator();it2.hasNext();)
				        {				        	
					        Element element2 = (Element) it2.next();
					        String name2 =element2.getName();
					        /*实例化item，装载具体的设备类型数据*/
					        BohuaDeviceType.Item item  =  new BohuaDeviceType.Item();
					        item.cmd = name2;
					        debug("2   "+name2);
				            for(Iterator it3=element2.attributeIterator();it3.hasNext();)
				            {
				                Attribute attribute = (Attribute) it3.next();
				                String name = attribute.getName();
				                String textx= attribute.getText();
				                String value = element2.getText();
				                debug(name+" =  "+textx +"    value="+value);
				                if(name.equals("type"))				item.type = textx;
				                else if(name.equals("length"))	item.length = textx;
				                else if(name.equals("desc"))		item.desc = textx;
				            }
					        if(name1.equals("base"))						deviceType.addBase(item);
					        else if(name1.equals("healthMessage"))			deviceType.addHealth(item);
					        else if(name1.equals("businessMessage"))		deviceType.addBusiness(item);
					        else if(name1.equals("commandMessage"))			deviceType.addCommand(item);
					        else if(name1.equals("register"))				deviceType.addRegister(item);
				        }
			        }
			 }
		}
         catch (DocumentException e) 
         {
			// TODO Auto-generated catch block
			e.printStackTrace();
         }  
         deviceType.disp();
         return deviceType;
	}
	
}
