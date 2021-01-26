package com.lx.attendance.config.shiro;

import com.lx.attendance.model.domain.ResourcesPathDO;
import com.lx.attendance.service.ResourcesPathService;
import org.apache.commons.lang3.StringUtils;
/*import org.apache.shiro.authc.credential.HashedCredentialsMatcher;*/
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

	// 日志定义
	private Logger logger = LoggerFactory.getLogger(ShiroConfig.class);
	// 往IOC容器中注入 ResourcesPathService;
	@Autowired(required = false)
	private ResourcesPathService resourcespathService;


	/**
	 * Shiro生命周期处理器
	 * 
	 * @return LifecycleBeanPostProcessor
	 */
	@Bean
	public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，因为在
	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
	 *
	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
	 * 3、部分过滤器可指定参数，如perms，roles
	 *
	 */
	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");

		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");

		// 未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/error.ftl");
		// 定义一个map存储权限
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		// 配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		// authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
		// anon表示无需登录即可访问
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/imgUpload/**", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/layui/**", "anon");
		filterChainDefinitionMap.put("/auth", "anon");
		filterChainDefinitionMap.put("/user/forgotPass", "anon");
		filterChainDefinitionMap.put("/user/verifyInfo", "anon");
		filterChainDefinitionMap.put("/user/updatePassword", "anon");
		filterChainDefinitionMap.put("/images/captcha", "anon");
		filterChainDefinitionMap.put("/email/captcha", "anon");
		filterChainDefinitionMap.put("/email/captcha2", "anon");
		filterChainDefinitionMap.put("/user/registered", "anon");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/logout", "anon");
		// authc表示需要登录才可以访问的路径
		filterChainDefinitionMap.put("/", "authc");
		filterChainDefinitionMap.put("/index", "authc");
		// 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 ;
		// 自定义加载权限资源关系
		List<ResourcesPathDO> resourcesList = resourcespathService.findAllResourcesList();
		for (ResourcesPathDO ResourcesPath : resourcesList) {
			if (StringUtils.isNotBlank(ResourcesPath.getPath())) {
				logger.info("新增权限  [url="+ResourcesPath.getPath()+"\tPermission="+ResourcesPath.getCode()+"]");
				String permission = "perms[" + ResourcesPath.getCode() + "]";
				filterChainDefinitionMap.put(ResourcesPath.getPath(), permission);
			}
		}
		// 除了以上的权限，其它全部权限只有角色是admin的用户才可以访问
		filterChainDefinitionMap.put("/**", "roles[admin]");
		// 将map集合存入shiro权限容器中
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 不指定名字的话，自动创建一个方法名第一个字母小写的bean
	 * 
	 * @return SecurityManager
	 */
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//在SecurityManager注入默认验证方法
		securityManager.setRealm(userRealm());
		//在SecurityManager注入使用的缓存
		securityManager.setCacheManager(ehCacheManager());
		return securityManager;
	}

	/**
	 * Shiro Realm继承自AuthorizingRealm的自定义Realm，即指定Shiro验证用户登录的类为自定义的
	 * 
	 * @return UserRealm
	 */
	@Bean("userRealm")
	public UserRealm userRealm() {
		UserRealm userRealm = new UserRealm();
/*		*//* 允许缓存 *//*
		userRealm.setCachingEnabled(true);
		*//* 允许认证缓存 *//*
		userRealm.setAuthenticationCachingEnabled(true);
		userRealm.setAuthenticationCacheName("authorization");
		*//* 允许授权缓存 *//*
		userRealm.setAuthorizationCachingEnabled(true);
		userRealm.setAuthorizationCacheName("authorization");*/
		return userRealm;
	}
	
	/**
	 * ehcache缓存管理器；shiro整合ehcache：
	 * 通过安全管理器：securityManager
	 * 单例的cache防止热部署重启失败
	 * @return EhCacheManager
	 */
	@Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:config/ehcache.xml");
        return em;
    }
	/**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1800000);//sesshion 存储时间为30分钟，如果30分钟内没有进行操作，则注销。
        return sessionManager;
    }

	/**
	 * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * 所以我们需要修改下doGetAuthenticationInfo中的代码; ）
	 * 
	 * @return
	 */
/*	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");// 散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(1);// 散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}*/

	/**
	 * 开启shiro aop注解支持. 使用代理方式;所以需要开启代码支持;
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
