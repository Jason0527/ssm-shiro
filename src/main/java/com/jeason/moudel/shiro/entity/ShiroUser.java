package com.jeason.moudel.shiro.entity;
/**
 * 简单的用户类
 * @author jason
 *
 */
public class ShiroUser {
	private String id;
	private String userNo;
	private String password;
	private String nickName;
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String toString(){
		return "user："+userNo;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
