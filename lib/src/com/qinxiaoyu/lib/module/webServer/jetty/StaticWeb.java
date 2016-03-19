package com.qinxiaoyu.lib.module.webServer.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class StaticWeb {

	public StaticWeb(int port,String html)
	{
		  Server server = new Server(port);
		  ResourceHandler resourceHandler = new ResourceHandler();
		  resourceHandler.setResourceBase(html);
		  server.setHandler(resourceHandler);
		  try 
		  {
			server.start();
		  }catch (Exception e) 
		  {
			e.printStackTrace();
		  }
	}
	
	
	
}
