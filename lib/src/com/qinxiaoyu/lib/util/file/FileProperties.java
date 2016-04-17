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
     *д��properties��Ϣ 
     * @param parameterName �����ļ������� 
     * @param parameterValue ��Ҫд��������ļ�����Ϣ 
     */  
    
    public void writeProperties(String filePath,String parameterName, String parameterValue)
    {
    	Properties prop = new Properties();  
    	InputStream fis = null;
    	OutputStream fos = null;
        try {  
            fis = new FileInputStream(filePath);  
            //���������ж�ȡ�����б�����Ԫ�ضԣ�  
            prop.load(fis);  
            //���� Hashtable �ķ��� put��ʹ�� getProperty �����ṩ�����ԡ�  
            //ǿ��Ҫ��Ϊ���Եļ���ֵʹ���ַ���������ֵ�� Hashtable ���� put �Ľ����  
            fos = new FileOutputStream(filePath);  
            prop.put(parameterName, parameterValue);  
            //���ʺ�ʹ�� load �������ص� Properties ���еĸ�ʽ��  
            //���� Properties ���е������б�����Ԫ�ضԣ�д�������  
            prop.store(fos, " Update '" + parameterName + "' value");  
           
            fos.close();
            fis.close();
        }  
        catch (IOException e) {  
            
            try {
            	if(fos != null)
            		fos.close();
            	if(fis != null)
            		fis.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            e.printStackTrace();
//	        	Print.print("ConfigInfoError","Visit "+filePath+" for updating "+parameterName+" value error");  
            System.err.println("**********************");  
            System.err.println("\r\n write BalanceStat configuration failed,please check "+filePath+" is writer . thank you \n\n");  
            System.err.println("**********************");  
//	            throw e;  
        }  
    }  
}
