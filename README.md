# SSM+Shiro的简单集成
shiro安全框架集认证、授权、加密、会话管理、与Web集成、缓存等于一身，提供一站式服务。
这个小案例主要体现啊的是shiro的登录认证以及权限管理功能
表：
	shiro_user   用户表（MD5加密前的密码都是11111111，盐值是userNo）
	shiro_user_role    用户角色表
	shiro_role_permission    角色权限表 
	
环境搭建：（详见代码）
	导入shiro jar包
	web.xml增加shiroFilter过滤器
	建立自定义realm类继承自AuthorizingRealm
	ehcache的xml配置文件
	spring+mvc+shiro的xml配置文件
	建表
	controller+service+dao+mybatis.xml
	（JSP页面）

