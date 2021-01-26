package com.lx.attendance.service;

import com.lx.attendance.model.UserDTO;
import com.lx.attendance.model.domain.RoleDO;
import com.lx.attendance.utils.ResultSet;

/**
 * 登录
 */
public interface LoginService {
    /**
     * 登录表单提交
     * @param UserDTO user, RoleDO roleDo
     * @return ResultSet
     */
	public ResultSet<String> authLogin(UserDTO user, RoleDO roleDo);

    /**
     * 退出登录
     * @return ResultSet
     */
    public ResultSet<String> logout();
}
