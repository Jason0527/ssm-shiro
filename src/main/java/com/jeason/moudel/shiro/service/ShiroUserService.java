package com.jeason.moudel.shiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeason.moudel.shiro.dao.ShiroUserDao;
import com.jeason.moudel.shiro.entity.ShiroUser;
@Service
public class ShiroUserService {
	@Autowired
	ShiroUserDao userDao;
	public ShiroUser getUserByNo(String userNo){
		return userDao.getUserByNo(userNo);
	}
	public List<String> getUserRoles(String userNo){
		return userDao.getUserRoles(userNo);
	}
	public List<String> getRolePermissions(String role){
		return userDao.getRolePermissions(role);
	}
	public List<String> getUserPermissions(String userNo){
		return userDao.getUserPermissions(userNo);
	}
	public int updateNickName(String userNo,String nickName){
		return userDao.updateNickName(userNo, nickName);
	}
}
