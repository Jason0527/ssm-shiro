package com.jeason.moudel.shiro.Controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeason.moudel.shiro.entity.ShiroUser;
import com.jeason.moudel.shiro.service.ShiroUserService;

/**
 * 测试shiro的controller
 * 
 * @author jason
 *
 */
@Controller
@RequestMapping("shiroUser")
public class ShiroUserController {
	@Autowired
	ShiroUserService userService;

	@RequestMapping(value = "/login")
	public String login(ShiroUser user) {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken upToken = new UsernamePasswordToken(user.getUserNo(), user.getPassword());
		upToken.setRememberMe(false);
		try {
			// 核心认证一步,此时调用realm里的登录认证方法
			currentUser.login(upToken);
			return "index";
		} catch (IncorrectCredentialsException ice) {
			System.out.println("账号/密码不匹配！");
		} catch (LockedAccountException lae) {
			System.out.println("账户已被冻结！");
		} catch (UnknownAccountException uae) {
			System.out.println("用户不存在！");
		} catch (Exception ae) {
			ae.printStackTrace();
			System.out.println(ae.getMessage());
		}
		return "login";
	}

	@RequestMapping(value = "/toLogin")
	public String toLogin() {
		return "login";
	}

	/**
	 * 有“view”权限的用户才能查看自己的信息
	 * 
	 * @param userNo
	 * @return
	 */
	@RequiresPermissions("view")//权限注解
	@ResponseBody
	@RequestMapping(value = "/view")
	public ShiroUser view(String userNo) {
		ShiroUser user = userService.getUserByNo(userNo);
		return user;
	}

	/**
	 * 有“update”权限的用户才能更改昵称 shiro权限控制注解介绍：
	 * RequiresAuthentication:使用该注解标注的类，实例，方法在访问或调用时，当前Subject必须在当前session中已经过认证。
	 * RequiresGuest:使用该注解标注的类，实例，方法在访问或调用时，当前Subject可以是“gust”身份，不需要经过认证或者在原先的session中存在记录。
	 * RequiresPermissions:当前Subject需要拥有某些特定的权限时，才能执行被该注解标注的方法。如果当前Subject不具有这样的权限，则方法不会被执行。
	 * RequiresRoles:当前Subject必须拥有所有指定的角色时，才能访问被该注解标注的方法。如果当天Subject不同时拥有所有指定角色，则方法不会执行还会抛出AuthorizationException异常。
	 * RequiresUser:当前Subject必须是应用的用户，才能访问或调用被该注解标注的类，实例，方法。
	 * 
	 * @param userNo
	 * @return
	 */
	@RequiresPermissions({ "update" })
	@ResponseBody
	@RequestMapping(value = "/updateNick")
	public ShiroUser updateNick(String nickName) {
		// 获取当前用户和他的客户号
		Subject currentUser = SecurityUtils.getSubject();
		ShiroUser user = (ShiroUser) currentUser.getPrincipal();
		String userNo = user.getUserNo();
		//修改昵称
		int n = userService.updateNickName(userNo, nickName);
		System.out.println(n);
		//返回修改后的用户信息
		ShiroUser returnUser = userService.getUserByNo(userNo);
		return returnUser;
	}
}
