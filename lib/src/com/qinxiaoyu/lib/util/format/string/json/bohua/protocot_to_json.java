package com.qinxiaoyu.lib.util.format.string.json.bohua;
//package com.qinxiaoyu.lib.format.string.json.bohua;
//
//
//import static test.json.Debug.*;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.util.LinkedHashMap;
//
//import com.google.gson.FieldNamingPolicy;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//
//public class protocot_to_json {
//
//	
//	public static void main(String argv[]){
//		
//		businessProtocol proto = new businessProtocol();
//		proto.protocol.setDev("PPBS", "0001", "1");
//		proto.protocol.setMsgtype("business");
//
//		LinkedHashMap<String, String> key1 = new LinkedHashMap<String, String>();
//		key1.put("key_word", "PPI");
//		key1.put("card_id", "5543");
//		key1.put("signal", "106");
//		key1.put("time", "2014:09:16 11:50:30:1234");
//		
//		LinkedHashMap<String, String> key2 = new LinkedHashMap<String, String>();
//		key2.put("key_word", "PPI");
//		key2.put("card_id", "6677");
//		key2.put("signal", "92");
//		key2.put("time", "2014:09:16 11:50:30:1256");
//		
//		proto.protocol.addData(key1);
//		proto.protocol.addData(key2);
//		
//		
//		proto.protocol.setEcho(12,"null");
//		proto.protocol.setCheck("crc","4d56");
//		
//		proto.protocol.setTime("2014:09:16 11:50:30:1111");
//		
//		try {
//			Class c =  Class.forName("com.bohua.base.protocol.data.protocol");
//			Object obj =  c.newInstance();
//			Field fielda = c.getField("time");
//			System.out.println("改变前 ： "+ fielda.get(obj));
//			fielda.set(obj,"1234");
//			System.out.println("改变后 ： "+ fielda.get(obj));
//			
////			Field[] nameField = c.getDeclaredFields();
////			for(int i=0;i<nameField.length;i++)
////			{
////				System.out.println(nameField[i]);
////			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchFieldException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
///*******************************************************************例1***************************************************************/
////测试1：@SerializedName 设置同一级的节点同名的时候，Gson会抛出错误
////		
////		Gson gson = new Gson();
////		String str = gson.toJson(proto);
////		debugi(str);
////		
////		
////		
////		Gson reGson = new Gson();
////		businessProtocol protoRead = new businessProtocol();
////		protoRead = reGson.fromJson(str, businessProtocol.class);
////		
////		
////		/*取出字符串*/
////		debugi("proto.protocol.check.type = "+protoRead.protocol.check.value);
////
////		for(int i = 0 ; i < protoRead.protocol.data.size() ; i++)
////		{
////			/*获取List中的数据*/
////			debugi("proto.protocol.data ["+i+"]  = "+protoRead.protocol.data.get(i));
////			
////			/*获取Map中的数据*/
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("key_word"));
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("card_id"));
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("signal"));
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("time"));
////		}
//		
///*******************************************************************例2***************************************************************/		
////实验1：当 setFieldNamingPolicy 设置时，按照原来的类无法解析，但不会抛出错误
////实验2：当 setVersion 设置时，被标记的项不会被序列化
////实验3：格式化		
//		
////		Gson gson = new GsonBuilder()  
////	        //.excludeFieldsWithoutExposeAnnotation() //不导出实体中没有用@Expose注解的属性  
////	        .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式  
////	        .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//时间转化为特定格式    
////	        //.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)//会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.  
////	        .setPrettyPrinting() //对json结果格式化.  
////	        .setVersion(1.0)     //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.  
////	                            	   //@Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么  
////	                            	   //@Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.  
////	        .create();  
////		String str = gson.toJson(proto);
////		debugi(str);
////		
////		
////		Gson reGson = new Gson();
////		businessProtocol protoRead = new businessProtocol();
////		protoRead = reGson.fromJson(str+"123123", businessProtocol.class);
////		/*取出字符串*/
////		debugi("proto.protocol.check.type = "+protoRead.protocol.check.value);
////		
////		if( protoRead == null )	debugi("protoRead = null ");
////
////		for(int i = 0 ; i < protoRead.protocol.data.size() ; i++)
////		{
////			/*获取List中的数据*/
////			debugi("proto.protocol.data ["+i+"]  = "+protoRead.protocol.data.get(i));
////			
////			/*获取Map中的数据*/
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("key_word"));
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("card_id"));
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("signal"));
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("time"));
////		}
//		
///*******************************************************************例3***************************************************************/		
//
//
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		
//	}
//}
