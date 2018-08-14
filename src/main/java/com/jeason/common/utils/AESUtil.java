package com.jeason.common.utils;


import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESUtil {

	//���key
	public static String generateKey(String sKey) throws UnsupportedEncodingException{
		if (sKey == null) {    
			System.out.print("KeyΪ��null");    
			return null;    
		}    
		// �ж�Key�Ƿ�Ϊ16λ    
		if (sKey.length() != 16) {    
			System.out.print("Key���Ȳ���16λ");    
			return null;    
		} 
		byte[] raw = sKey.getBytes("UTF-8");    
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		byte[]b=Base64.encodeBase64URLSafe(skeySpec.getEncoded());
		return new String(b,"UTF-8");
		//return Base64Util.base64EncodeByByte(skeySpec.getEncoded());
	}
	//���key����
	public static SecretKeySpec toKey(String sKey) throws Exception{
		if(sKey != null && sKey.length() > 0){
			byte[]b=new Base64().decode(sKey.getBytes("UTF-8"));
			//byte[]b=Base64Util.base64Decode(sKey);
			return new SecretKeySpec(b,"AES");
		}
		return null;
	}
	//����
	public static String encrypt(String sSrc, String sKey) throws Exception{
		SecretKeySpec skeySpec = toKey(sKey);
		if(skeySpec != null){
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"�㷨/ģʽ/���뷽ʽ"
			//IvParameterSpec iv = new IvParameterSpec("0132030305060708".getBytes());//ʹ��CBCģʽ����Ҫһ������iv�������Ӽ����㷨��ǿ��    
			IvParameterSpec iv = new IvParameterSpec(new Base64().decode(sKey.getBytes("UTF-8")));//ʹ��CBCģʽ����Ҫһ������iv�������Ӽ����㷨��ǿ��    
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);    
			//byte[] encrypted = cipher.doFinal(sSrc.getBytes());    
			byte[] encrypted = cipher.doFinal(Base64.encodeBase64URLSafe(sSrc.getBytes("UTF-8"))); 
			
			return new String(Base64.encodeBase64URLSafe(encrypted),"UTF-8");//�˴�ʹ��BAES64��ת�빦�ܣ�ͬʱ����2�μ��ܵ����á�
		}
		return null;
	}
	//����
	public static String decrypt(String sSrc, String sKey) throws Exception{
		SecretKeySpec skeySpec = toKey(sKey);
		if(skeySpec != null){
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");    
			//IvParameterSpec iv = new IvParameterSpec("0132030305060708".getBytes());  
			IvParameterSpec iv = new IvParameterSpec(new Base64().decode(sKey.getBytes("UTF-8")));
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);    
			byte[] encrypted1 = new Base64().decode(sSrc.getBytes("UTF-8"));//����bAES64����    
			byte[] original = cipher.doFinal(encrypted1);  
			//String originalString = new String(original,"UTF-8");   
			String originalString = new String(new Base64().decode(original),"UTF-8"); 
			return originalString; 
		}
		return null; 
	}
	//����
	public static void main(String[]args) throws Exception{
		/*JSONObject rdObj=new JSONObject();//�����ǩ�����
		rdObj.put("bibdb", "bxxxxxxx");
		rdObj.put("name", "xxxxxxx");
		System.out.println(rdObj.toString());
		//String en=Base64.encodeBase64String(rdObj.toString().getBytes());
		//System.out.println(en);
		String en=rdObj.toString();
		//en="Base64��/����";
		System.out.println(en);
		//System.out.println(generateKey(CommonUtil.generateRandomStr(16)));
		//System.out.println(decrypt("hWDU/0CXjVPQ9qvL1S1hUg==","MDEwMjAzMDQwNTA2MDcwOA=="));
		String key="OTcyYmVlNWM5NGI4MjMyNA==";
		String enStr=encrypt(en,key);
		System.out.println(enStr);
		String de=decrypt(enStr,key);
		System.out.println("���ܺ�"+de);
		//System.out.println(new String(Base64.decodeBase64(de)));
*/	
		
		System.out.println(HttpUtils.doHttpRequest("http://172.16.6.56:8080/tdyhapi/apiv1/ASSET_QUERY", "POST", 50000, "", "utf-8"));
	}
}
