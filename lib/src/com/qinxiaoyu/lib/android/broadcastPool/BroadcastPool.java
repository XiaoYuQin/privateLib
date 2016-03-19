/**
 * @author ������
 * @time 2015-3-13������
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
// * ʹ��˵����
// * 		����һ��android�㲥�շ����������ڶ�����߳��е���BroadcastPool.getReciveBundle()����ý��յ��Ĺ㲥��Service�̺߳�Activity�߳����벻Ҫʹ�ú�ʱ������
// * ʾ����
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
// * ʹ����Դ���߳�1����
// * */
/**
 * �����㲥��
 * ����ʱ�䣺2015/3/16
 * �޸�ʱ�䣺2015/3/19   ���������뺯�� moduleName �����޳��㲥���������ͺ�������յ��Ĺ㲥����
 * �޸�ʱ��: 2015/3/20	 ������ע����moduleName��ע�͡�ԭ�ȵ���˼Ϊ���˸õ�ַ�����ݣ���Ϊֻ���ոõ�ַ������
 * @author :������
 * @param : 
 * 		context�� �㲥�����������;
 * 		action�� �㲥���ռ����͵�action;
 * 		moduleName��ֻ����ͨ���Ľ������ݵ�ģ��;
 * @return : ��
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
		/*������������չ���������Ͳ�������յĹ������ظ�����ȡ��*/
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
	 * @author ������
	 * @exception:��bundle�����ж�ȡ�����bundle����
	 * @return:�������ݾ��򷵻�Bundleʵ�壬���������򷵻�null
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
	 * ��bundle���ݼ��뵽�㲥���������У��佫���Դ���BroadcastPoolʱ�����Action���㲥��ȥ��
	 * */
	public void setSendBundle(Bundle bundle)
	{
		debug("setSendBundle");
		sendBundleList.add(bundle);
		debug("sendBundleList ����="+sendBundleList.size());
	}
	
	
	/**
	 * ����ϵͳ�й㲥ActionΪthis.action�����й㲥�����ұ�����bundle�����С�
	 * �޸�ʱ�䣺2015/3/19 ����������������Ĺ㲥��
	 * �޸�ʱ�䣺2015/3/20 ������ҵ���߼�����(����������������Ĺ㲥),��if(!intent.getStringExtra("destination").equals(moduleName))
	 * 			��Ϊif(intent.getStringExtra("destination").equals(moduleName))��
	 * �޸�ʱ�䣺2015/3/23 ��3/20�ָĻ���ԭ����
	 * �޸�ʱ�䣺2015/4/27 ��onRecive�����ж��ڹ���������˴����׳�����䣬��ֹ�ڻ�ȡintent��ʱ����Ϊ����destination�ؼ��ֶ��׳�NullPointerException
	 * 			�쳣��
	 * */
	private class BohuaBroadcastReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context context, Intent intent) 
		{	
			String action = intent.getAction();
			debug("�㲥"+action+"�յ�����");
			/*ֻ���շ�������ģ�������*/
			try
			{
				Boolean flag = false;
				String destination = intent.getStringExtra("destination");
				for(String s:filterUnreciver)
				{
					if(s.equals(destination))
					{
						/*��ǰ���������ڲ�������յĹ������У����Ա����˵���*/
						flag = true;
						break;
					}
				}
				/*δ�����˵�����ͨ���˲����󣬽����ݱ��浽��ʱ���ݳ���*/
				if(flag == false)
				{
					for(String s:filterReciver)
					{
						if(s.equals(destination))
						{
							debug("�յ�һ�㲥������");
							Bundle bundle  = new Bundle();
							bundle = intent.getExtras();
							Set<String> keySet = bundle.keySet();
							debug("����bundle����="+keySet.size());
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
				debug("error ::û�ж�Ӧ��modle����");
			}

		}
	}
	/*
	 * ��this.actionΪAction���͹㲥��Ϣ
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
				debug("sendbroadcast over"+"   �㲥�����б�ĳ��� = "+sendBundleList.size());
				sendBundleList.remove(0);
				debug("remove sended sendbroadcast");
			}
		}
	}
	/*
	 * ��bundleͨ���㲥���ͳ�ȥ
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
