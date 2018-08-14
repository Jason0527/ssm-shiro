package com.jeason.common.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * MD5加密并Base64编码.
 * 使用加盐MD5，减少被穷举法破密的风险，使用固定的盐值，这样盐值不用存库
 * @author jason
 *
 */
public class Md5Util {
    //盐值
    private static final String salt = "jason";
    public static String MD5Encode(String s) {
	try {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] bytes = md.digest((s+salt).getBytes("utf-8"));
	    String hexString = toHex(bytes);
	   // return Base64.encodeBase64String(hexString.getBytes("UTF-8"));
	    return hexString;
	} catch (Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }
    /**
     * 将字节数组转为16进制字符串
     * @param bytes
     * @return
     */
    private static String toHex(byte[] bytes) {
	final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
	StringBuilder ret = new StringBuilder(bytes.length * 2);
	for (int i = 0; i < bytes.length; i++) {
	    ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
	    ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
	}
	return ret.toString();
    }
    public static void main(String[] args) {
	System.out.println(MD5Encode("1111111111"));
    }
}
