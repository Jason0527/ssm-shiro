package com.jeason.moudel.shiro.realm;


import java.util.List;


import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.jeason.moudel.shiro.entity.ShiroUser;
import com.jeason.moudel.shiro.service.ShiroUserService;
/**
 * 处理认证、授权类
 * @author jason
 *
 */
public class UserRealm extends AuthorizingRealm{
	@Resource
    ShiroUserService userService;
	@Override
	/**
	 * 权限管理。以下三种情况会执行此方法：
	 * 1、subject.hasRole(“admin”) 或 subject.isPermitted(“delete”)：自己去调用这个是否有什么角色或者是否有什么权限的时候；
	 * 2、@RequiresRoles("admin")/@RequiresPermissions("delete") ：在方法上加shiro注解的时候；
	 * 3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候。
	 */
	
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		//获取当前用户
		ShiroUser user = (ShiroUser)principals.getPrimaryPrincipal();
		String userNo = user.getUserNo();
		//获取该用户的所有角色
		List<String> roleList = userService.getUserRoles(userNo);
		//获取该用户的所有权限
		List<String> permissionList = userService.getUserPermissions(userNo);
		//将用户的角色和权限放到authorizationInfo中返回
		if(roleList != null && roleList.size()>0){
			authorizationInfo.addRoles(roleList);
		}
		if(permissionList != null && permissionList.size()>0){
			authorizationInfo.addStringPermissions(permissionList);
		}
		return authorizationInfo;
	}
	
	@Override
	/**
	 * 登录认证
	 * 当调用Subject currentUser = SecurityUtils.getSubject();currentUser.login(token)  时执行此方法
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		ShiroUser user = null;
        // 1. 把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        // 2. 从UsernamePasswordToken中获取userNo
        String userNo = upToken.getUsername();
        // 3. 若用户不存在，抛出UnknownAccountException异常
        user = userService.getUserByNo(userNo);
        if (user == null)
            throw new UnknownAccountException("用户不存在！");
        // 4.
        // 根据用户的情况，来构建AuthenticationInfo对象并返回，通常使用的实现类为SimpleAuthenticationInfo
        // realmName：当前realm对象的name，调用父类的getName()方法即可
        String realmName = getName();
        // 盐值：取用户信息中唯一的字段来生成盐值，避免由于两个用户原始密码相同，加密后的密码也相同
        ByteSource credentialsSalt = ByteSource.Util.bytes(userNo);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), credentialsSalt,
                realmName);
        return info;
	}

}
