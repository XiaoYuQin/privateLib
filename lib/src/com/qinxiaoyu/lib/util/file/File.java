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
	 * 创建一个文件夹
	 * @author    秦晓宇
	 * @date      2016年4月14日 下午2:46:38 
	 * @param file_path
	 * 			- 文件夹路径
	 * @return
	 * 			- true 创建文件成功；false 创建文件失败
	 */
	public static boolean create(String file_path){
		debug("create("+file_path+");");
		File file = new File(file_path);

		if(!file.exists() /*&& !file.isDirectory()*/){
			file.mkdir();
			debug("在SD卡中创建文件成功");
			debug("文件路径为"+file_path);
			return true;
		}
		else
		{
			debug("在SD卡中已有文件夹"+file_path);
			return false;
		}
	}
	


	/**
	 * 根据字符串数组创建文件或文件夹
	 * @author    秦晓宇
	 * @date      2016年4月14日 下午2:48:06 
	 * @param fileList
	 * 			- 文件或文件的路径组成的数组
	 */
	public static void createFiles(String[] fileList)
	{		
		debug("文件个数为"+fileList.length);
		for(int i=0;i<fileList.length;i++)
		{
			if(fileList[i] != null)
			{	
				boolean flag=create(fileList[i]);
				if(flag == true)
				{
					/**创建文件成功*/
					debug("创建文件"+fileList[i]+"成功");
				}
				else
				{
					debug("创建文件"+fileList[i]+"失败！！");
				}
			}
		}
	}
	

	/**
	 * 写文件
	 * @author    秦晓宇
	 * @date      2016年4月14日 下午2:50:17 
	 * @param filePath
	 * 			- 文件路径
	 * @param string
	 * 			- 文件内容
	 * @param type
	 * 			- type为true时写入文件末尾，type为false时清除原文件内容，重新写入
	 * @return
	 */
	public static boolean write(String filePath, String string, boolean type)
	{
		if(filePath == null)
		{
			debug("输入文件名为null");
			return false;
		}
		boolean ret = false;
		debug(filePath);

		//判断文件是否存在
		File file = new File(filePath);
		//文件不存在，根据路径创建文件夹和文件后写文件
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
		//文件存在,直接写文件
		else 
		{
			ret = writeStringToFile(filePath,string,type);
		}
		return ret;
	}


	/**
	 * 向一个文本中写入内容
	 * @author    秦晓宇
	 * @date      2016年4月18日 上午10:37:36 
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
			bw = new BufferedWriter(fw); // 将缓冲对文件的输出  
			bw.write(string + "\n"); // 写入文件  
			bw.newLine();  
	        bw.flush(); // 刷新该流的缓冲  
	        bw.close();  
	        fw.close();  
	        debug("写文件成功");    
	        ret = true;
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret = false;
		}	// 创建FileWriter对象，用来写入字符流  
    
        return ret;
	}
	/**
	 * 根据路径创建文件
	 * @author    秦晓宇
	 * @date      2016年4月18日 上午10:38:05 
	 * @param path
	 * 			- 文件路径 如："d:/offlineMap/config/MapPointsConfig.json"
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
		    	debug(pathString+"不存在");
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
		    	debug(pathString+"存在");
			}
		    pathString += "/";
		} 
		return ret;
	}
	
	/**
	 * 读文件
	 * @author    秦晓宇
	 * @date      2016年4月14日 下午3:00:32 
	 * @param filePath
	 * 			-文件路径
	 * @return
	 * 			- 文件内容。若未读到文件则返回null
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
			debug("读文件"+ret);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return ret;
	}
	/**
	 * 按照中文UTF8、GB2312格式读取文件
	 * @author    秦晓宇
	 * @date      2016年4月14日 下午3:01:35 
	 * @param filePath
	 * @param codeType
	 * 				- 文件格式
	 * @return		
	 * 				-文件内容，若未读到内容，这返回null
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
	            FileContent += "\r\n"; // 补上换行符 
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
	 * 根据全路径获得文件名称
	 * @author    秦晓宇
	 * @date      2016年4月14日 下午3:06:01 
	 * @param filePath
	 * @return
	 */
	public static String getFileNameByPath(String filePath)
	{
		String fileName = filePath.substring(filePath.lastIndexOf("/")+1);
		return fileName;
	}
	
	/**
	 * 获取文件大小
	 * @author    秦晓宇
	 * @date      2016年4月14日 下午3:05:57 
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
	 * 获取文件的拓展名
	 * @author    秦晓宇
	 * @date      2016年4月14日 下午3:07:07 
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
