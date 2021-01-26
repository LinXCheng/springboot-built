package com.lx.attendance.dao;

import com.lx.attendance.model.domain.UserDO;
import com.lx.attendance.model.vo.UserVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户管理
 */
public interface UserDao {

	/**
	 * 校验用户名是否已存在
	 * 
	 * @param username
	 * @return UserVO
	 */
	public UserDO queryExistUsername(@Param("username") String username, @Param("id") Long id);

	/**
	 * 新增用户
	 * 
	 * @param user
	 */
	public void addUser(UserDO user);

	/**
	 * 查询用户个人资料
	 * 
	 * @param id
	 *            用户id
	 * @return List<UserVO>
	 */
	public UserVO findUserInfo(@Param("id") Long id);

	/**
	 * 查询所有用户
	 * 
	 * @return List<UserVO>
	 */
	public List<UserVO> findAll(@Param("roleId") Integer roleId, @Param("keyword") String keyword);

	/**
	 * 根据用户id修改用户状态，0表示有效，1表示无效
	 * 
	 * @param id
	 *            用户id
	 * @param deleteStatus
	 *            用户状态
	 */
	public void changeStatus(@Param("id") Long id, @Param("deleteStatus") Integer deleteStatus);

	/**
	 * 根据用户id修改用户信息
	 * 
	 * @param user
	 * 用户实体对象
	 */
	public void updateUser(UserDO user);

	/**
	 * 根据用户id修改密码
	 * @param id
	 * @param password
	 */
	public void updatePassword(@Param("id") Long id, @Param("password") String password);

	/**
	 * 根据用户id修改用户信息
	 * @param user
	 * 用户实体对象
	 */
	public void updateUserInfo(UserDO user);

	/**
	 * 根据用户角色查询用户
	 * @param roleId
	 * @return
	 */
	public void updateUserRole(@Param("roleId") Integer roleId);

	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteUser(Long id);

}
