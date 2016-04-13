package com.qinxiaoyu.lib.util.format.string.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**Json �����Ĺ�����
 * @author ������
 * */
public class Json {

	private static void debug(Object obj){/*System.out.println(obj);*/}
	
	/**
	 * ����δ���       
	 * ��һ��ʵ����ת��Ϊһ��json�ַ���
	 * @param  object : ����ת��ΪJson��ʵ����
	 * */
	public static String toJson(Object object)
	{
		Gson gson = new Gson();
		String str = gson.toJson(object);
		debug(str);
		return str;
	}
	
	/**
	 * ����δ���  
	 * 
	 * ����һ��json�ַ�������һ�����ʵ�壬��jsonת��Ϊ���ʵ��
	 * @param obj : ת��Ϊʵ�����class
	 * @param json : ת��Ϊʵ��������Ҫ��json�ַ���
	 * */
	public static Object toObject(Object obj ,String json)
	{
		Gson reGson = new Gson();
		return reGson.fromJson(json, obj.getClass());
	}
	
	/**
	 * ��һ��json�ַ���ת��Ϊһ��ʵ����
	 * @author    	������
	 * @date      	2016��4��13�� ����5:22:45 
	 * @param c		
	 * 				-��Ҫת���� Class ����
	 * @param json	
	 * 				-��Ҫת����json�ַ���
	 * @return 		ת���ɹ����ʵ���࣬ת��ʧ�ܡ�����null
	 */
	public static Object toObject(Class c ,String json)
	{
		try
		{
			Gson reGson = new Gson();
			reGson.fromJson(json, c);
			return reGson;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
    /**
     * ����ת��Ϊjson��ʽ���ݣ���@Exposeע��Ļᱻ���л���
     * ʹ�õ���Gson��GsonBuilder��ʽ���������л���
     * ����ʱ�䣺2015/3/18 
     * @author :������
     * @param : netType ���͵ķ�ʽ ��ip �Է���ip��ַ��port�Է��Ľ��ն˿ڣ�input���͵�����
     * @return boolean: ���ͳɹ��򷵻�true���������ٷ���false��
     * */
	public static String builderToJson(Object object)
	{
		Gson gson = new GsonBuilder()  
		.excludeFieldsWithoutExposeAnnotation() 						//������ʵ����û����@Exposeע�������  
		.enableComplexMapKeySerialization() 							//֧��Map��keyΪ���Ӷ������ʽ  
		.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")		//ʱ��ת��Ϊ�ض���ʽ    
		//.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)		//����ֶ�����ĸ��д,ע:����ʵ����ʹ����@SerializedNameע��Ĳ�����Ч.  
		.setPrettyPrinting() 											//��json�����ʽ��.  
		.setVersion(1.0)    											//�е��ֶβ���һ��ʼ���е�,�����Ű汾��������ӽ���,��ô�ڽ������л��ͷ����л���ʱ��ͻ���ݰ汾����ѡ���Ƿ�Ҫ���л�.  
																		//@Since(�汾��)��������ʵ���������.�����ֶο���,���Ű汾��������ɾ��,��ô  
																		//@Until(�汾��)Ҳ��ʵ���������,GsonBuilder.setVersion(double)������Ҫ����.  
		.create();  
		String str = gson.toJson(object);
		return str;
	}
	
	
	/**
	 * ת��Ϊ����ʽ�������Json�ַ���
	 * @author    ������
	 * @date      2016��4��13�� ����5:01:55 
	 * @param object
	 * @return
	 */
	public static String toJsonByPretty(Object object)
	{
		Gson gson = new GsonBuilder()  
		//.excludeFieldsWithoutExposeAnnotation() 						//������ʵ����û����@Exposeע�������  
		.enableComplexMapKeySerialization() 							//֧��Map��keyΪ���Ӷ������ʽ  
		.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")		//ʱ��ת��Ϊ�ض���ʽ    
		//.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)		//����ֶ�����ĸ��д,ע:����ʵ����ʹ����@SerializedNameע��Ĳ�����Ч.  
		.setPrettyPrinting() 											//��json�����ʽ��.  
		//.setVersion(1.0)    											//�е��ֶβ���һ��ʼ���е�,�����Ű汾��������ӽ���,��ô�ڽ������л��ͷ����л���ʱ��ͻ���ݰ汾����ѡ���Ƿ�Ҫ���л�.  
																		//@Since(�汾��)��������ʵ���������.�����ֶο���,���Ű汾��������ɾ��,��ô  
																		//@Until(�汾��)Ҳ��ʵ���������,GsonBuilder.setVersion(double)������Ҫ����.  
		.create();  
		String str = gson.toJson(object);
		return str;
	}
	
}
