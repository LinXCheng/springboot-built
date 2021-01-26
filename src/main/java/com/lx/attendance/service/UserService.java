package com.lx.attendance.service;

import com.github.pagehelper.PageInfo;
import com.lx.attendance.model.domain.UserDO;
import com.lx.attendance.model.vo.UserVO;

import java.util.List;

/**
 * 用户管理
 */
public interface UserService {

    /**
     * 新增用户
     * @param user 用户实体对象
     */
    public void addUser(UserDO user);

    /**
     * 检测用户名是否已存在
     * @param username 用户名
     * @return UserVO
     */
    public UserDO queryExistUsername(String username, Long id);
    /**
     * 根据用户id查询用户个人资料
     * @param id 用户id
     * @return List<UserVO>
     */
    public UserVO findUserInfo(Long id);

    /**
     * 查询所有用户
     * @param pageNum
     * @param pageSize
     * @param roleId
     * @param keyword
     * @return
     */
    public PageInfo<UserVO> findAll(int pageNum, int pageSize, Integer roleId, String keyword);

    /**
     *查询所有用户
     * @param roleId
     * @param keyword
     * @return
     */
    public List<UserVO> findAll(Integer roleId, String keyword);

    /**
     * 根据用户id修改用户信息
     * @param user 用户实体对象
     */
	public void updateUser(UserDO user);
    /**
     * 根据用户id修改密码
     * @param id
     * @param password
     */
    public void updatePassword(Long id, String password);

    /**
     * 根据用户id修改用户信息
     * @param user
     * 用户实体对象
     */
    public void updateUserInfo(UserDO user);

    /**
     * 根据用户id修改用户状态，0表示有效，1表示无效
     * @param id
     */
    public void changeStatus(Long id, Integer deleteStatus);

    /**
     * 修改用户角色为roleId的用户为null
     * @param roleId
     */
    public void updateUserRole(Integer roleId);

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(Long id);

}
