package com.qinxiaoyu.lib.module.fifo;

import java.util.ArrayList;

import com.qinxiaoyu.lib.Debug;




/**
 * ！！！问题，无法获得类具体占用的内存，所以无法测试数据集对于内存的占用
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
	 * Fifo的两种数据丢弃策略
	 * 创建时间：2015/4/14
	 * @author :秦晓宇
	 **/
	public static enum FIFO_STRATEGY{
		HEAD_DROP,				/*头部丢弃 : 当外部数据太多时丢弃先入的数据*/
		TAIL_DROP,				/*尾部丢弃 : 当外部数据太多时不保存这些数据*/
		NO_DROP
	}
	
	
	/**
	 * Fifo的存储器
	 * 创建时间：2015/4/14
	 * @author :秦晓宇
	 **/
	private ArrayList<Object> data;
	
	/**
	 * Fifo大最大存储容量
	 * 创建时间：2015/4/14
	 * @author :秦晓宇
	 **/
	private int maxSize = 0;
	
	/**
	 * Fifo当前的存储量
	 * 创建时间：2015/4/14
	 * @author :秦晓宇
	 **/
	private int nowSize = 0;
	
//	/**
//	 * Fifo剩余的容量
//	 * 创建时间：2015/4/14
//	 * @author :秦晓宇
//	 **/
//	private int remainSize = 0;
	
	/**
	 * Fifo是否被设置过参数的标志位
	 * 创建时间：2015/4/14
	 * @author :秦晓宇
	 **/
	private Boolean isParamed = false;
	
	private FIFO_STRATEGY fifoStrategy = FIFO_STRATEGY.HEAD_DROP;
	
	/**
	 * Fifo的构造器
	 * 创建时间：2015/4/14
	 * @author :秦晓宇
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
	 * Fifo参数设置器,设置这个fifo最大的容量，以字节为单位。当设置容量小于等于0时，返回错误。
	 * 创建时间：2015/4/14
	 * @author : 秦晓宇
	 * @param : maxSize fifo的最大存储容量
	 * @return : 设置参数成功返回true，设置参数失败返回false。
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
	 * 1.将数据写入这个数据集的数据池中，同时记录当前Fifo中数据有多少。
	 * 2.当fifo无法容纳写入的数据量时，根据FIFO_STATE选择丢弃头部数据还是尾部数据。
	 * 3.当没有setParam时则不对长度及丢弃数据做处理，所有数据一并存入内存中。
	 * 创建时间：2015/4/14
	 * @author :秦晓宇
	 * @param : maxSize fifo的最大存储容量。
	 * @return : 若写入成功则返回true，写入失败则返回false。 容纳不下也返回false。
	 **/
	 public Boolean write(Object data)
	 {
		 debug("缓冲写入数据");
		 if(this.isParamed)
		 {
			 debug("未配置Fifo丢弃数据模式，接收到数据");
			 /*写入数据长度大于fifo的所有大小*/
			 if(data.toString().length()>this.maxSize)	return false;
			 /*写入数据长度比现在大小大，写不进去了！*/
			 if(this.nowSize+data.toString().length()>this.maxSize)	
			 {
				 switch(this.fifoStrategy)
				 {
				 	 /*头部丢弃，丢弃成功后保存数据到内存中*/
					 case HEAD_DROP :
						 	Boolean result = headDrop(data.toString().length());
						 	if(result == true)	this.data.add(data);
						 	this.nowSize+=data.toString().length();
						 	debug("HEAD_DROP nowSize = "+this.nowSize);
						 break;
					 /*尾部丢弃，不保存数据，不做操作*/
					 case TAIL_DROP :
						 break;
					 case NO_DROP:
						 
						 break;
					 default:
						 debug("有数据进入，但什么都没做");
						 break;
				 }
			 }
			 /*有足够的内存写入data数据*/
			 else
			 {
				 this.nowSize+=data.toString().length();
				 debug("配置后write nowSize = "+this.nowSize);
				 this.data.add(data);
			 }
		 }
		 else
		 {
			 this.data.add(data);
			 this.nowSize+=data.toString().length();
			 debug("未被配置write nowSize = "+this.nowSize);
		 }
		 return true;
	 }

	 /**
	 * 读取Fifo的一条数据<hr>
	 * @author    秦晓宇
	 * @date      2016年4月22日 下午2:55:46 
	 * @return
	 * 			- 有值时返回实体类<br>
	 * 			- 无值时返回nll
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
		 * 头部丢弃数据的遍历算法，size表示需要空出来的容量。
		 * 创建时间：2015/4/14
		 * @author :秦晓宇
		 * @param : maxSize fifo的最大存储容量。
		 * @return : 若写入成功则返回true，写入失败则返回false。 
		 **/
	 private Boolean headDrop(int size)
	 {
//		0.先看输入的size是不是比整个fifo的maxSize还要大，如果比maxSize还要大，则返回false，并且不处理data链表。
//		1.如果输入的size比maxSize要小，则执行下述流程。
//			1.首先查看是否有需要多的容量。
//			2.如果有如此多的空间，则退出方法。
//			3.如果没有上述的空间，则从前到后遍历链表，数出删掉多少个链表项才够空间。
//			4.执行数出数量的循环，从头删掉该数量的链表项。
		 
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
