/**
 * @author 秦晓宇
 * @time 2015-3-13：创建
 * */
package com.qinxiaoyu.lib.android.broadcastPool;

import java.util.Set;
import java.util.Vector;

import com.qinxiaoyu.lib.Debug;
import com.qinxiaoyu.lib.android.LibBundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;




///**
// * 使用说明：
// * 		创建一个android广播收发器。必须在额外的线程中调用BroadcastPool.getReciveBundle()来获得接收到的广播。Service线程和Activity线程中请不要使用耗时操作。
// * 示例：
// * 
//
//	public class MainActivity extends ActionBarActivity 
//	{
//		BroadcastPool broadcastRcvPool;
//	 	protected void onCreate(Bundle savedInstanceState) 
//	 	{
//			super.onCreate(savedInstanceState);
//			setContentView(R.layout.activity_main);
//	
//			broadcastRcvPool = new BroadcastPool(getApplicationContext(),"BOHUA_BROADCAST");
//			BroadcastPool broadcastSendPool = new BroadcastPool(getApplicationContext(),"BOHUA_BROADCAST");
//			Bundle bundle1 = new Bundle();
//			bundle1.putString("key1", "value1");
//			bundle1.putString("key2", "value2");
//			broadcastSendPool.setSendBundle(bundle1);
//			MyThread myThread = new MyThread();
//			myThread.start();
//		}
//		
//		private class MyThread extends Thread
//		{	
//			@Override
//			public void run()
//			{
//				Bundle bundle2 = new Bundle();
//				while(true)
//				{
//					bundle2 = broadcastRcvPool.getReciveBundle();
//					if(bundle2 != null)
//					{
//						
//						Log.i("testbroadcast","key1"+bundle2.getString("key1")); 
//						Log.i("testbroadcast","key2"+bundle2.getString("key2"));
//					} 
//				}
//			}
//		}
//	}
// * 
// * 
// * 使用资源：线程1个。
// * */
/**
 * 创建广播池
 * 创建时间：2015/3/16
 * 修改时间：2015/3/19   增加了输入函数 moduleName 用于剔除广播发送器发送后，自身会收到的广播数据
 * 修改时间: 2015/3/20	 修正了注释中moduleName的注释。原先的意思为过滤该地址的数据，现为只接收该地址的数据
 * @author :秦晓宇
 * @param : 
 * 		context： 广播必须的上下文;
 * 		action： 广播接收及发送的action;
 * 		moduleName：只允许通过的接收数据的模块;
 * @return : 无
 **/
public class BroadcastPool{

	private Vector<Bundle> rcvBundleList;
	private Vector<Bundle> sendBundleList;
	
	private Vector<String> filterReciver;
	private Vector<String> filterUnreciver;
	
	public Context context;
	private String action;
//	private String moduleName;
	
	private void debug(String s){if(Debug.debugBroadcastRcvPool)	Debug.debugx("BroadcastPool",s);}
	

	public BroadcastPool(Context context,String action)
	{
		this.action = action;
//		this.moduleName = moduleName;
		debug("init BroadcastPool ok");
		rcvBundleList = new Vector<Bundle>();
		sendBundleList = new Vector<Bundle>();
		filterReciver = new Vector<String>();
		filterUnreciver = new Vector<String>();
		this.context = context;
		BohuaBroadcastReceiver bohuaBroadcastReciver = new BohuaBroadcastReceiver();
		context.registerReceiver(bohuaBroadcastReciver,new IntentFilter(this.action));
		BohuaBroadcastSendThreader bohuaBroadcastSender = new BohuaBroadcastSendThreader();
		bohuaBroadcastSender.start();
	}
	
	public void setFilterRecive(String ...reviceFilter)
	{
		/*后设置允许接收过滤器如果和不允许接收的过滤器重复，则取消*/
		for(String s:reviceFilter)
		{
			for(String x:filterUnreciver)
			{
				if(s.equals(x))
				{
					this.filterUnreciver.remove(x);
					break;
				}
			}
		}
		for(String s:reviceFilter)
		{
			Boolean flag = false;
			for(String x:filterReciver)
			{
				if(s.equals(x))
				{
					flag = true;
					break;
				}
			}
			if(flag == false)
			{
				this.filterReciver.add(s);
			}
		}
		
	}
	public void setFilterUnrecive(String ...unreciveFilter)
	{
		for(String s:unreciveFilter)
		{
			for(String x:filterReciver)
			{
				if(s.equals(x))
				{
					this.filterReciver.remove(x);
					break;
				}
			}
		}
		for(String s:unreciveFilter)
		{
			Boolean flag = false;
			for(String x:filterUnreciver)
			{
				if(s.equals(x))
				{
					flag = true;
					break;
				}
			}
			if(flag == false)
			{
				this.filterUnreciver.add(s);
			}
		}
	}
	
	
	/**
	 * @author 秦晓宇
	 * @exception:从bundle链表中读取先入的bundle数据
	 * @return:若有数据据则返回Bundle实体，若无数据则返回null
	 * */
	public Bundle getReciveBundle()
	{
		if(rcvBundleList.size()>0)
		{
			Bundle bundle = new Bundle();
			bundle = this.rcvBundleList.get(0);
			this.rcvBundleList.remove(0);
			return bundle;
		}
		else
		{
			return null;	
		}
	}
	/**
	 * 将bundle数据加入到广播发送链表中，其将会以创建BroadcastPool时输入的Action来广播出去。
	 * */
	public void setSendBundle(Bundle bundle)
	{
		debug("setSendBundle");
		sendBundleList.add(bundle);
		debug("sendBundleList 长度="+sendBundleList.size());
	}
	
	
	/**
	 * 接收系统中广播Action为this.action的所有广播，并且保存于bundle链表中。
	 * 修改时间：2015/3/19 过滤了来自于自身的广播。
	 * 修改时间：2015/3/20 修正了业务逻辑错误(过滤了来自于自身的广播),将if(!intent.getStringExtra("destination").equals(moduleName))
	 * 			改为if(intent.getStringExtra("destination").equals(moduleName))。
	 * 修改时间：2015/3/23 将3/20又改回了原来。
	 * 修改时间：2015/4/27 在onRecive函数中对于过滤器添加了错误抛出的语句，防止在获取intent的时候因为少了destination关键字而抛出NullPointerException
	 * 			异常。
	 * */
	private class BohuaBroadcastReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent) 
		{	
			String action = intent.getAction();
			debug("广播"+action+"收到数据");
			/*只接收发送至本模块的数据*/
			try
			{
				Boolean flag = false;
				String destination = intent.getStringExtra("destination");
				for(String s:filterUnreciver)
				{
					if(s.equals(destination))
					{
						/*当前这条数据在不允许接收的过滤器中，所以被过滤掉了*/
						flag = true;
						break;
					}
				}
				/*未被过滤的数据通过滤波器后，将数据保存到临时数据池中*/
				if(flag == false)
				{
					for(String s:filterReciver)
					{
						if(s.equals(destination))
						{
							debug("收到一广播条数据");
							Bundle bundle  = new Bundle();
							bundle = intent.getExtras();
							Set<String> keySet = bundle.keySet();
							debug("缓存bundle长度="+keySet.size());
//							for(String key : keySet) 
//							{
//								Object value = bundle.get(key);
//							    debug(""+value.getClass());
//								debug(key+" = "+value.toString());
//							}
//							
							rcvBundleList.add(bundle);
							break;
						}
					}
				}
			}
			catch(NullPointerException e)
			{
				debug("error ::没有对应的modle名称");
			}

		}
	}
	/*
	 * 以this.action为Action发送广播消息
	 * */
	private void bohuaBroadcastSender()
	{
		while(true)
		{
			if(sendBundleList.size()>0)
			{
				debug("bohuaBroadcastSender");
				Intent intent = new Intent();
				intent.setAction(this.action);
				intent.putExtras(sendBundleList.get(0));
				LibBundle.displayAll(sendBundleList.get(0));
				context.sendBroadcast(intent);
				debug("sendbroadcast over"+"   广播发送列表的长度 = "+sendBundleList.size());
				sendBundleList.remove(0);
				debug("remove sended sendbroadcast");
			}
		}
	}
	/*
	 * 将bundle通过广播发送出去
	 * */
	private class BohuaBroadcastSendThreader extends Thread
	{
		@Override
		public void run()
		{
			bohuaBroadcastSender();
		}
	}
}
