package com.jeason.common.utils;

import java.util.UUID;

/**
 * 生成uuid作为主键
 * @author jason
 *
 */
public class IdGen {
	public static String createUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
