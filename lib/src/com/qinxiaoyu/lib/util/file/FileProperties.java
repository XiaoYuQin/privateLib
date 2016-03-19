package com.qinxiaoyu.lib.util.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class FileProperties {

	public String readProperties(String filePath , String key)
	{
		String prop_string;
		
		Properties prop = new Properties();
		prop = loadConfig(filePath);
		prop_string = (String) prop.get(key);
		return prop_string;
	}
	
	private Properties loadConfig(String file) {  
		Properties properties = new Properties();  
		try {  
			FileInputStream s = new FileInputStream(file);  
			properties.load(s);  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
		return properties;  
	}  
	
	
	 /** 
     *写入properties信息 
     * @param parameterName 配置文件属性名 
     * @param parameterValue 需要写入的配置文件的信息 
     */  
    
    public void writeProperties(String filePath,String parameterName, String parameterValue)
    {
    	Properties prop = new Properties();  
        try {  
            InputStream fis = new FileInputStream(filePath);  
            //从输入流中读取属性列表（键和元素对）  
            prop.load(fis);  
            //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
            //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。  
            OutputStream fos = new FileOutputStream(filePath);  
            prop.put(parameterName, parameterValue);  
            //以适合使用 load 方法加载到 Properties 表中的格式，  
            //将此 Properties 表中的属性列表（键和元素对）写入输出流  
            prop.store(fos, " Update '" + parameterName + "' value");  
           
        }  
        catch (IOException e) {  
//	        	Print.print("ConfigInfoError","Visit "+filePath+" for updating "+parameterName+" value error");  
            System.err.println("**********************");  
            System.err.println("\r\n write BalanceStat configuration failed,please check "+filePath+" is writer . thank you \n\n");  
            System.err.println("**********************");  
//	            throw e;  
        }  
    }  
}
