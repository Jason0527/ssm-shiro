package com.jeason.common.utils;


import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;


public class Base64Util {
	/*public static String base64Encode(String data) throws UnsupportedEncodingException{  
        return new String(Base64.encodeBase64(data.getBytes()),"UTF-8");
    } 
	public static String base64EncodeByByte(byte[] data) throws UnsupportedEncodingException{  
        return new String(Base64.encodeBase64(data),"UTF-8");  
    } 
    public static byte[] base64Decode(String data){  
        return Base64.decodeBase64(data.getBytes());  
    }  
    public static byte[] base64DecodeByByte(byte[] data){
        return Base64.decodeBase64(data); 
    }*/
	public static String base64Encode(String data) throws UnsupportedEncodingException{  
        return new String(Base64.encodeBase64URLSafe(data.getBytes()),"UTF-8");
    } 
	public static String base64EncodeByByte(byte[] data) throws UnsupportedEncodingException{  
        return new String(Base64.encodeBase64URLSafe(data),"UTF-8");  
    } 
    public static byte[] base64Decode(String data){  
        return new Base64().decode(data.getBytes());
    }  
    public static byte[] base64DecodeByByte(byte[] data){
        return Base64.decodeBase64(data); 
    }
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param args
	 * @throws JSONException 
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws JSONException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		JSONObject rdObj=new JSONObject();
		rdObj.put("bid", "bxxxxxxx");
		rdObj.put("name", "xxxxxxx");
		String en=base64Encode(rdObj.toString());
		System.out.println(en);
		System.out.println(new String(base64Decode(en)));
	}

}
