package com.lx.attendance.config.shiro;

import com.lx.attendance.model.domain.RoleDO;
import com.lx.attendance.model.domain.UserDO;
import com.lx.attendance.model.vo.UserVO;
import com.lx.attendance.service.RoleService;
import com.lx.attendance.service.UserService;
import com.lx.attendance.utils.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * shiro自定义过滤器（已经未使用，如需使用需要在shiroConfig配置）
 * @author Administrator
 *
 */
@Component
public class URLPathMatchingFilter extends PathMatchingFilter {
	private Logger log = LoggerFactory.getLogger(URLPathMatchingFilter.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	public static URLPathMatchingFilter urlFilter;

	@PostConstruct
	public void init() {
		urlFilter = this;
		urlFilter.roleService = this.roleService;
		urlFilter.userService = this.userService;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		try {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			// 请求的url
			String requestURL = getPathWithinApplication(request);
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			UserDO OldUserInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
			log.info(OldUserInfo.getUsername() + "请求的url: " + requestURL + " 成功");
			if (!subject.isAuthenticated()) {
				log.info("当前用户未登陆!");
				WebUtils.issueRedirect(request, response, "/login");
				return false;
			}
			UserVO userInfo = urlFilter.userService.findUserInfo(OldUserInfo.getId());
			RoleDO roleVO = urlFilter.roleService.findRoleById(OldUserInfo.getRoleId());
			if (userInfo.getDeleteStatus() != 0) {// 判断用户是否有效
				log.info("当前用户失效!");
				writer(httpRequest, httpResponse, OldUserInfo.getUsername() + "失效!");
				return false;
			} else if (userInfo.getRoleId() == null) {// 判断用户是否分配角色
				log.info("当前用户未分配角色!");
				writer(httpRequest, httpResponse, OldUserInfo.getUsername() + "未分配角色!");
				return false;
			} else if (roleVO.getState() == 1) {// 判断角色是否禁用
				log.info("当前用户被禁用!");
				writer(httpRequest, httpResponse, OldUserInfo.getUsername() + "被禁用!");
				return false;
			}
			Map<String, String> userPermission = (Map<String, String>) session
					.getAttribute(Constants.SESSION_USER_PERMISSION);
			if (userPermission.get(requestURL) != null) {
				return true;
			} else {
				log.info(OldUserInfo.getUsername() + "没有访问路径 " + requestURL + " 的权限");
				WebUtils.issueRedirect(request, response, "/error");
				return false;
			}
		} catch (Exception e) {
			log.info("当前用户未登陆!");
			WebUtils.issueRedirect(request, response, "/login");
			return false;
		}
	}

	public void writer(HttpServletRequest request, HttpServletResponse response, String info) throws IOException {
		String str = "<script language='javascript'>alert('" + info + "');"
				+ "window.top.location.href='/login';</script>";
		response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
		PrintWriter writer = response.getWriter();
		writer.write(str);
		writer.flush();
	}
}
