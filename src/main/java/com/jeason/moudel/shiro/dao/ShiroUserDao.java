package com.jeason.moudel.shiro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jeason.moudel.shiro.entity.ShiroUser;

public interface ShiroUserDao {
	/**
	 * 通过账号获取用户信息
	 * @param userNo
	 * @return
	 */
	public ShiroUser getUserByNo(String userNo);
	/**
	 * 获取用户的角色
	 * @param userNo
	 * @return
	 */
	public List<String> getUserRoles(String userNo);
	/**
	 * 获取角色对应的权限
	 * @param role
	 * @return
	 */
	public List<String> getRolePermissions(String role);
	/**
	 * 获取用户的权限
	 */
	public List<String> getUserPermissions(String userNo);
	/**
	 * 更新用户昵称
	 */
	public int updateNickName(@Param("userNo")String userNo,@Param("nickName")String nickName);
}
