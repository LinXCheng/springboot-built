package com.lx.attendance.config.shiro;

import com.lx.attendance.model.domain.RoleDO;
import com.lx.attendance.model.domain.UserDO;
import com.lx.attendance.service.ResourcesPathService;
import com.lx.attendance.service.RoleService;
import com.lx.attendance.service.UserService;
import com.lx.attendance.utils.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private ResourcesPathService resourcesPathService;
	@Autowired
	private RoleService roleService;

	/**
	 * 查看用户权限
	 * 
	 * @param principals
	 * @return AuthorizationInfo
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Session session = SecurityUtils.getSubject().getSession();
		List<String> permissionsCode = (List<String>)session.getAttribute(Constants.SESSION_USER_CODE);
		String roleCode = (String)session.getAttribute(Constants.SESSION_USER_ROLE);
		if (permissionsCode.size() == 0) {
			UserDO userDO = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
			permissionsCode = resourcesPathService.findAllCodeList(userDO.getId());
		}
 		// 为当前用户设置角色和权限
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addStringPermissions(permissionsCode);
		authorizationInfo.addRole(roleCode);
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的Subject，LoginController.login()方法中执行Subject.login()时执行此方法
	 * 
	 * @param authcToken
	 * @return AuthenticationInfo
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		String loginName = (String) authcToken.getPrincipal();
		// 获取用户密码
		// String password = new String((char[]) authcToken.getCredentials());
		UserDO user = userService.queryExistUsername(loginName, null);
		if (user == null) {
			// 没找到帐号
			throw new UnknownAccountException("用户名不存在!");
		}
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
		// 将数据库中存在的加密过的密码字符串与在Service中存在UsernamePasswordToken中密码进行比对
		 SimpleAuthenticationInfo authenticationInfo = null;
		 authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
		 user.getPassword(), getName());
		 //session中不需要保存密码
		 user.setPassword(null);
		// 将用户信息放入session中
		Session session = SecurityUtils.getSubject().getSession();
		session.setTimeout(1800000);
		session.setAttribute(Constants.SESSION_USER_INFO, user);
		RoleDO roleDO = roleService.findRoleById(user.getRoleId());
		session.setAttribute(Constants.SESSION_USER_ROLE, roleDO.getRoleCode());
		session.setAttribute(Constants.SESSION_USER_NAME, user.getUsername());
		List<String> userCode = resourcesPathService.findAllCodeList(user.getId());
		session.setAttribute(Constants.SESSION_USER_CODE, userCode);
		return authenticationInfo;
	}

	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
}
