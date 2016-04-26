package com.qinxiaoyu.lib.module.fifo;

import java.util.ArrayList;

import com.qinxiaoyu.lib.Debug;




/**
 * ���������⣬�޷���������ռ�õ��ڴ棬�����޷��������ݼ������ڴ��ռ��
 * 
 * 
 * */
public class Fifo {
	
	private void debug(String str)
	{
		if(Debug.debugModuleFifo)
		{
			Debug.debugx("Fifo", str);
		}
	}

	
	/**
	 * Fifo���������ݶ�������
	 * ����ʱ�䣺2015/4/14
	 * @author :������
	 **/
	public static enum FIFO_STRATEGY{
		HEAD_DROP,				/*ͷ������ : ���ⲿ����̫��ʱ�������������*/
		TAIL_DROP,				/*β������ : ���ⲿ����̫��ʱ��������Щ����*/
		NO_DROP
	}
	
	
	/**
	 * Fifo�Ĵ洢��
	 * ����ʱ�䣺2015/4/14
	 * @author :������
	 **/
	private ArrayList<Object> data;
	
	/**
	 * Fifo�����洢����
	 * ����ʱ�䣺2015/4/14
	 * @author :������
	 **/
	private int maxSize = 0;
	
	/**
	 * Fifo��ǰ�Ĵ洢��
	 * ����ʱ�䣺2015/4/14
	 * @author :������
	 **/
	private int nowSize = 0;
	
//	/**
//	 * Fifoʣ�������
//	 * ����ʱ�䣺2015/4/14
//	 * @author :������
//	 **/
//	private int remainSize = 0;
	
	/**
	 * Fifo�Ƿ����ù������ı�־λ
	 * ����ʱ�䣺2015/4/14
	 * @author :������
	 **/
	private Boolean isParamed = false;
	
	private FIFO_STRATEGY fifoStrategy = FIFO_STRATEGY.HEAD_DROP;
	
	/**
	 * Fifo�Ĺ�����
	 * ����ʱ�䣺2015/4/14
	 * @author :������
	 **/
	public Fifo()
	{
		this.data = new ArrayList<Object>();
		this.nowSize = 0;
		this.maxSize = 0;
		this.fifoStrategy = FIFO_STRATEGY.HEAD_DROP;
		this.isParamed = false;
	}
	
	/**
	 * Fifo����������,�������fifo�������������ֽ�Ϊ��λ������������С�ڵ���0ʱ�����ش���
	 * ����ʱ�䣺2015/4/14
	 * @author : ������
	 * @param : maxSize fifo�����洢����
	 * @return : ���ò����ɹ�����true�����ò���ʧ�ܷ���false��
	 **/
	public Boolean setParam(int maxSize,FIFO_STRATEGY fifoStrategy)
	{
		if(maxSize>0)		this.maxSize = maxSize;
		else							return false;
		this.fifoStrategy = fifoStrategy;
		data = new ArrayList<Object>();
		isParamed = true;
		return true;
	}
	
	/**
	 * 1.������д��������ݼ������ݳ��У�ͬʱ��¼��ǰFifo�������ж��١�
	 * 2.��fifo�޷�����д���������ʱ������FIFO_STATEѡ����ͷ�����ݻ���β�����ݡ�
	 * 3.��û��setParamʱ�򲻶Գ��ȼ�����������������������һ�������ڴ��С�
	 * ����ʱ�䣺2015/4/14
	 * @author :������
	 * @param : maxSize fifo�����洢������
	 * @return : ��д��ɹ��򷵻�true��д��ʧ���򷵻�false�� ���ɲ���Ҳ����false��
	 **/
	 public Boolean write(Object data)
	 {
		 debug("����д������");
		 if(this.isParamed)
		 {
			 debug("δ����Fifo��������ģʽ�����յ�����");
			 /*д�����ݳ��ȴ���fifo�����д�С*/
			 if(data.toString().length()>this.maxSize)	return false;
			 /*д�����ݳ��ȱ����ڴ�С��д����ȥ�ˣ�*/
			 if(this.nowSize+data.toString().length()>this.maxSize)	
			 {
				 switch(this.fifoStrategy)
				 {
				 	 /*ͷ�������������ɹ��󱣴����ݵ��ڴ���*/
					 case HEAD_DROP :
						 	Boolean result = headDrop(data.toString().length());
						 	if(result == true)	this.data.add(data);
						 	this.nowSize+=data.toString().length();
						 	debug("HEAD_DROP nowSize = "+this.nowSize);
						 break;
					 /*β�����������������ݣ���������*/
					 case TAIL_DROP :
						 break;
					 case NO_DROP:
						 
						 break;
					 default:
						 debug("�����ݽ��룬��ʲô��û��");
						 break;
				 }
			 }
			 /*���㹻���ڴ�д��data����*/
			 else
			 {
				 this.nowSize+=data.toString().length();
				 debug("���ú�write nowSize = "+this.nowSize);
				 this.data.add(data);
			 }
		 }
		 else
		 {
			 this.data.add(data);
			 this.nowSize+=data.toString().length();
			 debug("δ������write nowSize = "+this.nowSize);
		 }
		 return true;
	 }

	 /**
	 * ��ȡFifo��һ������<hr>
	 * @author    ������
	 * @date      2016��4��22�� ����2:55:46 
	 * @return
	 * 			- ��ֵʱ����ʵ����<br>
	 * 			- ��ֵʱ����nll
	 */
	public Object read()
	 {
		 if(this.data.size()>0)
		 {
			 Object o = new Object();
			 o = this.data.get(0);
			 this.data.remove(0);
			 this.nowSize-=o.toString().length();
			 debug("read nowSize = "+this.nowSize);
			 return o;
		 }
		 return null;
	 }
	 
		/**
		 * ͷ���������ݵı����㷨��size��ʾ��Ҫ�ճ�����������
		 * ����ʱ�䣺2015/4/14
		 * @author :������
		 * @param : maxSize fifo�����洢������
		 * @return : ��д��ɹ��򷵻�true��д��ʧ���򷵻�false�� 
		 **/
	 private Boolean headDrop(int size)
	 {
//		0.�ȿ������size�ǲ��Ǳ�����fifo��maxSize��Ҫ�������maxSize��Ҫ���򷵻�false�����Ҳ�����data����
//		1.��������size��maxSizeҪС����ִ���������̡�
//			1.���Ȳ鿴�Ƿ�����Ҫ���������
//			2.�������˶�Ŀռ䣬���˳�������
//			3.���û�������Ŀռ䣬���ǰ���������������ɾ�����ٸ�������Ź��ռ䡣
//			4.ִ������������ѭ������ͷɾ���������������
		 
		 if(size>this.maxSize)		return false;
		 else if(size == this.maxSize)
		 {
			 for(int i =0;i<this.data.size();i++)
			 {
				 this.nowSize -= this.data.get(i).toString().length();
				 this.data.remove(i);
			 }
		 }
		 else
		 {
			 int countSize = 0;
			 for(int i =0;i<this.data.size();i++)
			 {
				 countSize+=this.data.get(i).toString().length();
				 this.nowSize -= this.data.get(i).toString().length();
				 this.data.remove(i);
				 if(countSize >= size)
				 {
					 break;
				 }
			 }
		 }
		 return true;
	 }

	 
}
