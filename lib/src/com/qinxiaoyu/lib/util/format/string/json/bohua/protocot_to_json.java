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
//			System.out.println("�ı�ǰ �� "+ fielda.get(obj));
//			fielda.set(obj,"1234");
//			System.out.println("�ı�� �� "+ fielda.get(obj));
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
///*******************************************************************��1***************************************************************/
////����1��@SerializedName ����ͬһ���Ľڵ�ͬ����ʱ��Gson���׳�����
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
////		/*ȡ���ַ���*/
////		debugi("proto.protocol.check.type = "+protoRead.protocol.check.value);
////
////		for(int i = 0 ; i < protoRead.protocol.data.size() ; i++)
////		{
////			/*��ȡList�е�����*/
////			debugi("proto.protocol.data ["+i+"]  = "+protoRead.protocol.data.get(i));
////			
////			/*��ȡMap�е�����*/
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("key_word"));
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("card_id"));
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("signal"));
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("time"));
////		}
//		
///*******************************************************************��2***************************************************************/		
////ʵ��1���� setFieldNamingPolicy ����ʱ������ԭ�������޷��������������׳�����
////ʵ��2���� setVersion ����ʱ������ǵ���ᱻ���л�
////ʵ��3����ʽ��		
//		
////		Gson gson = new GsonBuilder()  
////	        //.excludeFieldsWithoutExposeAnnotation() //������ʵ����û����@Exposeע�������  
////	        .enableComplexMapKeySerialization() //֧��Map��keyΪ���Ӷ������ʽ  
////	        .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//ʱ��ת��Ϊ�ض���ʽ    
////	        //.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)//����ֶ�����ĸ��д,ע:����ʵ����ʹ����@SerializedNameע��Ĳ�����Ч.  
////	        .setPrettyPrinting() //��json�����ʽ��.  
////	        .setVersion(1.0)     //�е��ֶβ���һ��ʼ���е�,�����Ű汾��������ӽ���,��ô�ڽ������л��ͷ����л���ʱ��ͻ���ݰ汾����ѡ���Ƿ�Ҫ���л�.  
////	                            	   //@Since(�汾��)��������ʵ���������.�����ֶο���,���Ű汾��������ɾ��,��ô  
////	                            	   //@Until(�汾��)Ҳ��ʵ���������,GsonBuilder.setVersion(double)������Ҫ����.  
////	        .create();  
////		String str = gson.toJson(proto);
////		debugi(str);
////		
////		
////		Gson reGson = new Gson();
////		businessProtocol protoRead = new businessProtocol();
////		protoRead = reGson.fromJson(str+"123123", businessProtocol.class);
////		/*ȡ���ַ���*/
////		debugi("proto.protocol.check.type = "+protoRead.protocol.check.value);
////		
////		if( protoRead == null )	debugi("protoRead = null ");
////
////		for(int i = 0 ; i < protoRead.protocol.data.size() ; i++)
////		{
////			/*��ȡList�е�����*/
////			debugi("proto.protocol.data ["+i+"]  = "+protoRead.protocol.data.get(i));
////			
////			/*��ȡMap�е�����*/
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("key_word"));
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("card_id"));
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("signal"));
////			debugi("	proto.protocol.data.key ["+i+"]  = "+protoRead.protocol.data.get(i).get("time"));
////		}
//		
///*******************************************************************��3***************************************************************/		
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
