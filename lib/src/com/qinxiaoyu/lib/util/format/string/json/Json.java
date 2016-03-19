package com.qinxiaoyu.lib.util.format.string.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**Json 操作的工具类
 * @author 秦晓宇
 * */
public class Json {

	private static void debug(Object obj){/*System.out.println(obj);*/}
	
	/**
	 * ！！未完成       
	 * 将一个实体类转换为一个json字符串
	 * @param  object : 用来转换为Json的实体类
	 * */
	public static String toJson(Object object)
	{
		Gson gson = new Gson();
		String str = gson.toJson(object);
		debug(str);
		return str;
	}
	
	/**
	 * ！！未完成  
	 * 
	 * 输入一条json字符串，和一个类的实体，将json转换为类的实体
	 * @param obj : 转换为实体类的class
	 * @param json : 转换为实体类所需要的json字符串
	 * */
	public static Object toObject(Object obj ,String json)
	{
		Gson reGson = new Gson();
		return reGson.fromJson(json, obj.getClass());
	}
    /**
     * 把类转换为json格式数据，有@Expose注解的会被序列化。
     * 使用的是Gson的GsonBuilder方式来进程序列化；
     * 创建时间：2015/3/18 
     * @author :秦晓宇
     * @param : netType 发送的方式 ，ip 对方的ip地址，port对方的接收端口，input发送的数据
     * @return boolean: 发送成功则返回true，发送数百返回false。
     * */
	public static String builderToJson(Object object)
	{
		Gson gson = new GsonBuilder()  
		.excludeFieldsWithoutExposeAnnotation() 						//不导出实体中没有用@Expose注解的属性  
		.enableComplexMapKeySerialization() 							//支持Map的key为复杂对象的形式  
		.serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")		//时间转化为特定格式    
		//.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)		//会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.  
		.setPrettyPrinting() 											//对json结果格式化.  
		.setVersion(1.0)    											//有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.  
																		//@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么  
																		//@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.  
		.create();  
		String str = gson.toJson(object);
		return str;
	}
	
}
