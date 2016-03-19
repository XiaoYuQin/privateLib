package com.qinxiaoyu.lib.module.transmit.net.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.qinxiaoyu.lib.util.event.BaseEventSource;

public class TcpClient{

	private Socket s;
	private BufferedWriter bw;
	private BufferedReader br;
//	private TcpClientSource tcpClientSource;
	private BaseEventSource source;
	private String ip;
	private int port;

	public Boolean open(String ip, int port,BaseEventSource eventSource) 
	{
	    try
	    {
	    		this.ip = ip;
	    		this.port = port;
//	    		tcpClientSource = eventSource ;
	    		this.source = eventSource;
				s = new Socket(ip, port);  
				bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		      	br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		      	System.out.println("TcpClientReciver thread statr");  
		      	new Thread(new TcpClientReciver()).start();
		      	return true;
		} 
	    catch (IOException e) 
	    {
			e.printStackTrace();
			return false;
		}  
	}

	public Boolean close() {return false;}

	public void write(String data) 
	{
		try 
		{
			bw.write(data);
			bw.flush();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public String read() {return null;}

	public void ioctl() {}
	
	/*接收tcp数据线程*/
	private class TcpClientReciver extends Thread
	{
		public void run()
		{
			System.out.print("TcpClientReciver");  
			while(true)
			{
				try
				{
					int c = 0;
		            while ((c = br.read()) > -1) 
		            {  
//		            	netEventSource.setNotifies(c);
		            	source.setNotifies(c);
		            	System.out.println(c+"");
		            }  
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}

	public void write(Byte[] data) {
		// TODO Auto-generated method stub
		
	}
	
	public Boolean isSame(String ip,int port)
	{
		if(ip.equals(this.ip)&&(port == this.port))
			return true;
		else
			return false;
	}
}
