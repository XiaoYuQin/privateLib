package com.qinxiaoyu.lib.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import com.qinxiaoyu.lib.Debug;

public class LibFile {

	public static enum CodeType
	{
		UTF8,
		GB2312
	}
	
	
	private static void debug(String str)
	{
		if(Debug.debugFileFile)
		{
			Debug.debugx("File", str);
		}
	}
	
	
	/**
	 * 写入数据到文件中
	 * **/
	public static int write(String filePath, String string, boolean type)
	{
		FileWriter fw = null;
		BufferedWriter bw = null;
		try
		{
			fw = new FileWriter(filePath,type);		// 创建FileWriter对象，用来写入字符流  
			bw = new BufferedWriter(fw); 			// 将缓冲对文件的输出  
			bw.write(string + "\n"); 				// 写入文件  
			bw.newLine();  
            bw.flush(); 							// 刷新该流的缓冲  
            bw.close();  
            fw.close();  
            debug("写文件成功");
			
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			try
			{  
				bw.close();  
	            fw.close();  
	        } 
			catch (IOException e1) 
	        {  
                // TODO Auto-generated catch block  
	        }  
		}
		return 0;
	}
	/**
	 * 从文件中读出数据
	 * **/
	public static String read(String filePath,CodeType codeType)
	{
		String FileContent = "";
		String codeTypeTemp = "";
		switch(codeType)
		{
			case UTF8:
				codeTypeTemp = "UTF-8";
				break;
			case GB2312:
				codeTypeTemp = "GB2312";
				break;
		}
	    try { 
	    	FileInputStream fis = new FileInputStream(filePath); 
	    	InputStreamReader isr;
	    	if(codeTypeTemp.equals(""))
	    		isr = new InputStreamReader(fis/*, "UTF-8"*/); 
	    	else
	    		isr = new InputStreamReader(fis, codeTypeTemp); 
	        BufferedReader br = new BufferedReader(isr); 
	        String line = ""; 
	        while ((line = br.readLine()) != null) { 
	            FileContent += line; 
	            FileContent += "\r\n"; // 补上换行符 
	        } 
	        br.close();
	        isr.close();
	        fis.close();
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    }
//		debug(FileContent);
//		System.out.println(FileContent);
		return FileContent ;
	}

	public static String read(String filePath)
	{
		String ret= "";
		/**获取SD卡中文件路径*/
    	
		FileReader fr = null;
		BufferedReader br = null;
		try 
		{
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);

			try 
			{
				while(br.readLine() != null)
					ret += br.readLine();
		        br.close();  
		        fr.close();
			} 
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			debug("读文件"+ret);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	
	
}
