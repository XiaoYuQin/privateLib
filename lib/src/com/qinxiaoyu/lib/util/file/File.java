package com.qinxiaoyu.lib.util.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.regex.Pattern;

import com.qinxiaoyu.lib.Debug;

public class File extends java.io.File{
	
	/***/
	private static final long serialVersionUID = 1L;
	public File(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	static String tag = "File";
	private static void debug(String string){if(Debug.debugFile == true) Debug.debugx(tag,string);}
	
	public static enum CodeType
	{
		UTF8,
		GB2312
	}
	
	

	/**
	 * ����һ���ļ���
	 * @author    ������
	 * @date      2016��4��14�� ����2:46:38 
	 * @param file_path
	 * 			- �ļ���·��
	 * @return
	 * 			- true �����ļ��ɹ���false �����ļ�ʧ��
	 */
	public static boolean create(String file_path){
		debug("create("+file_path+");");
		File file = new File(file_path);

		if(!file.exists() /*&& !file.isDirectory()*/){
			file.mkdir();
			debug("��SD���д����ļ��ɹ�");
			debug("�ļ�·��Ϊ"+file_path);
			return true;
		}
		else
		{
			debug("��SD���������ļ���"+file_path);
			return false;
		}
	}
	


	/**
	 * �����ַ������鴴���ļ����ļ���
	 * @author    ������
	 * @date      2016��4��14�� ����2:48:06 
	 * @param fileList
	 * 			- �ļ����ļ���·����ɵ�����
	 */
	public static void createFiles(String[] fileList)
	{		
		debug("�ļ�����Ϊ"+fileList.length);
		for(int i=0;i<fileList.length;i++)
		{
			if(fileList[i] != null)
			{	
				boolean flag=create(fileList[i]);
				if(flag == true)
				{
					/**�����ļ��ɹ�*/
					debug("�����ļ�"+fileList[i]+"�ɹ�");
				}
				else
				{
					debug("�����ļ�"+fileList[i]+"ʧ�ܣ���");
				}
			}
		}
	}
	

	/**
	 * д�ļ�
	 * @author    ������
	 * @date      2016��4��14�� ����2:50:17 
	 * @param filePath
	 * 			- �ļ�·��
	 * @param string
	 * 			- �ļ�����
	 * @param type
	 * 			- typeΪtrueʱд���ļ�ĩβ��typeΪfalseʱ���ԭ�ļ����ݣ�����д��
	 * @return
	 */
	public static boolean write(String filePath, String string, boolean type)
	{
		if(filePath == null)
		{
			debug("�����ļ���Ϊnull");
			return false;
		}
		boolean ret = false;
		debug(filePath);

		//�ж��ļ��Ƿ����
		File file = new File(filePath);
		//�ļ������ڣ�����·�������ļ��к��ļ���д�ļ�
		if(!file.exists())
		{
			if(creatFileByPath(filePath) == true)
			{
				ret = writeStringToFile(filePath, string, type);
			}
			else
			{
				ret = false;
			}
		}
		//�ļ�����,ֱ��д�ļ�
		else 
		{
			ret = writeStringToFile(filePath,string,type);
		}
		return ret;
	}


	/**
	 * ��һ���ı���д������
	 * @author    ������
	 * @date      2016��4��18�� ����10:37:36 
	 * @param filePath
	 * @param string
	 * @param type
	 * @return
	 */
	private static boolean writeStringToFile(String filePath, String string, boolean type)
	{
		boolean ret = false;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try 
		{
			fw = new FileWriter(filePath,type);
			bw = new BufferedWriter(fw); // ��������ļ������  
			bw.write(string + "\n"); // д���ļ�  
			bw.newLine();  
	        bw.flush(); // ˢ�¸����Ļ���  
	        bw.close();  
	        fw.close();  
	        debug("д�ļ��ɹ�");    
	        ret = true;
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = false;
		}	// ����FileWriter��������д���ַ���  
    
        return ret;
	}
	/**
	 * ����·�������ļ�
	 * @author    ������
	 * @date      2016��4��18�� ����10:38:05 
	 * @param path
	 * 			- �ļ�·�� �磺"d:/offlineMap/config/MapPointsConfig.json"
	 * @return
	 */
	public static boolean creatFileByPath(String path)
	{
		boolean ret = false;
		Pattern pattern = Pattern.compile("/");
		String[] strs = pattern.split(path);
		String pathString = "";
		debug(""+strs.length);
		for (int i=0;i<strs.length;i++) 
		{
		    System.out.println(strs[i]);
		    pathString += strs[i];		    
		    debug("pathString = "+i+"  "+ pathString);
		    File file = new File(pathString);
		    if(!file.exists())
		    {
		    	debug(pathString+"������");
		    	if(i!=strs.length-1)
		    	{
		    		file.mkdir();
		    	}
		    	else 
		    	{
		    		try {
						file.createNewFile();
						ret = true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						ret = false;
					}
				}
		    }
		    else 
		    {
		    	debug(pathString+"����");
			}
		    pathString += "/";
		} 
		return ret;
	}
	
	/**
	 * ���ļ�
	 * @author    ������
	 * @date      2016��4��14�� ����3:00:32 
	 * @param filePath
	 * 			-�ļ�·��
	 * @return
	 * 			- �ļ����ݡ���δ�����ļ��򷵻�null
	 */
	public static String read(String filePath)
	{
		if(filePath == null) return null;
		String ret = null;
		
		File file = new File(filePath);
    	if(!file.exists())
		{ 
			try 
			{
				file.createNewFile();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
    	
		FileReader fr = null;
		BufferedReader br = null;
		try 
		{
			fr = new FileReader(filePath);
			br = new BufferedReader(fr);
			String s = null;
			try 
			{
				while((s = br.readLine()) != null)
				{
					s = s+"\n";
					ret += s;
				}
		        br.close();  
		        fr.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			debug("���ļ�"+ret);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * ��������UTF8��GB2312��ʽ��ȡ�ļ�
	 * @author    ������
	 * @date      2016��4��14�� ����3:01:35 
	 * @param filePath
	 * @param codeType
	 * 				- �ļ���ʽ
	 * @return		
	 * 				-�ļ����ݣ���δ�������ݣ��ⷵ��null
	 */
	public static String read(String filePath,CodeType codeType)
	{
		if(filePath == null) return null;
		String FileContent = null;
		String codeTypeTemp = "";
		switch(codeType)
		{
			case UTF8:
				codeTypeTemp = "UTF-8";
				break;
			case GB2312:
				codeTypeTemp = "GB2312";
				break;
			default:
				codeTypeTemp = "UTF-8";
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
	            FileContent += "\r\n"; // ���ϻ��з� 
	        } 
	        br.close();
	        isr.close();
	        fis.close();
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    }
		return FileContent ;
	}
	/**
	 * ����ȫ·������ļ�����
	 * @author    ������
	 * @date      2016��4��14�� ����3:06:01 
	 * @param filePath
	 * @return
	 */
	public static String getFileNameByPath(String filePath)
	{
		String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
		return fileName;
	}
	
	/**
	 * ��ȡ�ļ���С
	 * @author    ������
	 * @date      2016��4��14�� ����3:05:57 
	 * @param file
	 * @return	
	 */
	public static String getFileSize(File file)
	{
		   long fileSize = file.length();
           if(fileSize > 1024*1024) 
           {
		       float size = fileSize /(1024f*1024f);
		       return new DecimalFormat("#.00").format(size) + "MB";
           } 
           else if(fileSize >= 1024) 
           {
              float size = fileSize/1024;
              return new DecimalFormat("#.00").format(size) + "KB";
           } 
           else 
           {
              return fileSize + "B";
           }
	}
	
	/**
	 * ��ȡ�ļ�����չ��
	 * @author    ������
	 * @date      2016��4��14�� ����3:07:07 
	 * @param file
	 * @return
	 */
	public String getFileSuffix(File file) 
	{
		String type = "*/*";
		String fileName = file.getName();
		int dotIndex = fileName.indexOf('.');
		debug("indexOf "+dotIndex);
		if((dotIndex >-1) && (dotIndex < (fileName.length() - 1))) 
		{    
            return fileName.substring(dotIndex + 1);    
        }    
		return type;
	}
	
	
	
}
