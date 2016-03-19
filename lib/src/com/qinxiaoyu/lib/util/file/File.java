package com.qinxiaoyu.lib.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.qinxiaoyu.lib.Debug;

public class File {

	private static void debug(String str)
	{
		if(Debug.debugFileFile)
		{
			Debug.debugx("File", str);
		}
	}
	
	
	/**
	 * д�����ݵ��ļ���
	 * **/
	public static int write(String filePath, String string, boolean type)
	{
		FileWriter fw = null;
		BufferedWriter bw = null;
		try
		{
			fw = new FileWriter(filePath,type);		// ����FileWriter��������д���ַ���  
			bw = new BufferedWriter(fw); 			// ��������ļ������  
			bw.write(string + "\n"); 				// д���ļ�  
			bw.newLine();  
            bw.flush(); 							// ˢ�¸����Ļ���  
            bw.close();  
            fw.close();  
            debug("д�ļ��ɹ�");
			
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
	 * ���ļ��ж�������
	 * **/
	public static String read(String filePath)
	{
		String ret= "";
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
			debug("���ļ�"+ret);
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	
}
