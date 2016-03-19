package com.qinxiaoyu.lib.format.string.json.bohua_V1_0;

import java.util.ArrayList;

import com.qinxiaoyu.lib.format.string.date.Date;


/**
 * 博华科技的json数据的格式类，描述了数据传输过程中的数据格式
 * 创建时间：2015/3/19
 * 修改时间：2015/3/23
 * @author :秦晓宇
 * @param : 
 * @return :
 * */
public class BHProtocol{
	
	 
	private Message message;
	public BHProtocol()				{message  = new Message();}
	public Message getMessage()		{return message;}
	
	public class Message{

		private Base base;		 
		private ArrayList<BusinessMessage> business;
		private ArrayList<HealthMessage> health;
		private ArrayList<CommandMessage> command;
		private ArrayList<CommandMessageECO> commandECO;
		
		public Message()
		{
			base = new Base();
			business = new ArrayList<BusinessMessage>();
			health= new ArrayList<HealthMessage>();
			command= new ArrayList<CommandMessage>();
			commandECO= new ArrayList<CommandMessageECO>();
		}
		public void setBase(int version,int device_id,String check_type,String check_value)
		{
			base.version = version;
			base.device_id = device_id;
			base.check_type = check_type;
			base.check_value = check_value;
			base.send_time = Date.sysDateToString();
		}
		public void addBusiness(int id,String business_class,Double business_value)
		{
			BusinessMessage business = new BusinessMessage();
			business.id = id;
			business.business_class = business_class;
			business.business_value = business_value;
			business.business_time = Date.sysDateToString();
			this.business.add(business);
		}
		public void addHealth(int id,String health_class,Double health_value)
		{
			HealthMessage health = new HealthMessage();
			health.id = id;
			health.health_class = health_class;
			health.health_value = health_value;
			health.health_time = Date.sysDateToString();
			this.health.add(health);
		}
		public void addCommand(int id,String command_class,Double command_value)
		{
			CommandMessage command = new CommandMessage();
			command.id = id;
			command.command_class = command_class;
			command.command_value = command_value;
			command.command_time = Date.sysDateToString();
			this.command.add(command);
		}	
		public void addCommandECO(int id,String command_ECon)
		{
			CommandMessageECO commandECO = new CommandMessageECO();
			commandECO.id = id;
			commandECO.command_ECon = command_ECon;
			commandECO.command_ECon_time = Date.sysDateToString();
			this.commandECO.add(commandECO);
		}		
		public Base getBase()								{return base;}
		public ArrayList<BusinessMessage> getBusiness()		{return business;}
		public ArrayList<HealthMessage> getHealth()			{return health;}
		public ArrayList<CommandMessage> getCommand()		{return command;}
		public ArrayList<CommandMessageECO> getCommandECO()	{return commandECO;}
		public int getVerison()								{return base.version;}
		
		public class Base
		{
			private int version;
			private int device_id;
			private String check_type;
			private String check_value;
			private String send_time;
			
			public int getVerison()			{return version;}
			public int getDeviceId()		{return device_id;}
			public String getCheckType()	{return check_type;}
			public String getChecktValue()	{return check_value;}
			public String getSendTime()		{return send_time;}
		}

		public class BusinessMessage
		{
			 
			private int id ;
			private String business_class ;
			private Object business_value ;
			private String business_time ;
			
			public int getId()				{return id;}
			public String getBusinessClass(){return business_class;}
			public Object getBusinessValue(){return business_value;}
			public String getBusinessTime()	{return business_time;}
		}
		
		public class HealthMessage{
			private int id ;
			private String health_class ;
			private Object health_value ;
			private String health_time ;
			
			public int getId()				{return id;}
			public String getHealthClass()	{return health_class;}
			public Object getHealthValue()	{return health_value;}
			public String getHealthTime()	{return health_time;}
		}
		
		public class CommandMessage{
			 
			int id ;
			private String command_class ;
			private Object command_value ;
			private String command_time ;
			
			public int getId()				{return id;}
			public String getCommandClass()	{return command_class;}
			public Object getCommandValue()	{return command_value;}
			public String getCommandTime()	{return command_time;}
		}
		
		public class CommandMessageECO{
			 
			int id ;
			String command_ECon ;
		    String command_ECon_time ;
		    
			public int getId()					{return id;}
			public String getCommandEConClass()	{return command_ECon;}
			public Object getcommandEConTime()	{return command_ECon_time;}
		}
		
	}
	
}
