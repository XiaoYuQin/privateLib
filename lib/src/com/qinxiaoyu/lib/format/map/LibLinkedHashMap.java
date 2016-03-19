package com.qinxiaoyu.lib.format.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class LibLinkedHashMap {

	private static void debug(String obj)
	{
		System.out.println("LibLinkedHashMap : "+obj);
	}

	public static Boolean isHaveKey(String key,Map<?, ?> map)
	{
		Set<?> keySet = map.keySet();											//���ؼ��ļ��� 
		Iterator<?> it = keySet.iterator(); 
		while(it.hasNext())      															//��һ�ֵ�����ʽȡ��ֵ 
		{ 
			Object keyRead = it.next(); 
			if(key.equals(keyRead))
			{
				return true;
			}
		} 
		return false;
	}
	
	public static ArrayList<Object> getkey(LinkedHashMap<?, ?> map)
	{
		ArrayList<Object> list = new ArrayList<Object>();
		Set<?> keySet = map.keySet();											//���ؼ��ļ��� 
		Iterator<?> it = keySet.iterator(); 
		while(it.hasNext())      															//��һ�ֵ�����ʽȡ��ֵ 
		{ 
			Object key = it.next(); 
			list.add(key);
			System.out.println(key+" : "+map.get(key)); 			//���ݼ���ȡ��Ӧ��ֵ 
		} 
		return list;
	}
	
	public static boolean equal(LinkedHashMap<?, ?> map1,LinkedHashMap<?, ?> map2)
	{
		if(map1 == map2)	return true;
		if(map1.equals(map2))	return true;
		
		if(map1.size() != map2.size())	
		{
			return false;
		}
		else
		{
			Set<?> keySet1 = map1.keySet();											//���ؼ��ļ��� 
			Iterator<?> it1 = keySet1.iterator(); 
			
			Set<?> keySet2 = map2.keySet();											//���ؼ��ļ��� 
			Iterator<?> it2 = keySet2.iterator(); 
			
			while(it1.hasNext())      															//��һ�ֵ�����ʽȡ��ֵ 
			{ 
				Object key1 = it1.next(); 
				Object key2 = it2.next(); 
				
				if(!key1.equals(key2))
				{
					debug("key1="+key1+"   key2"+key2);
					return false;
				}
				if(!map1.get(key1).equals(map2.get(key2)))
				{
					debug("map1.get(key1) = "+map1.get(key1)+"    map2.get(key2)"+map2.get(key2));
					return false;
				}
//				System.out.println(key1+" : "+map1.get(key1)); 			//���ݼ���ȡ��Ӧ��ֵ 
			} 
		}
		return true;
	}
	
	
}
