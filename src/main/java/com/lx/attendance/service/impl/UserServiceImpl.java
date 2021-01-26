package com.lx.attendance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.attendance.model.domain.UserDO;
import com.lx.attendance.model.vo.UserVO;
import com.lx.attendance.service.UserService;
import com.lx.attendance.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户管理
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 新增用户
     * @param user
     */
    @Override
    public void addUser(UserDO user) {
        userDao.addUser(user);
    }


    /**
     * 校验用户名是否已存在
     * @param username
     * @return UserVO
     */
	@Override
	public UserDO queryExistUsername(String username, Long id) {
		return userDao.queryExistUsername(username, id);
	}

    /**
     * 根据用户id查询用户信息
     * @param id 用户id
     * @return UserVO
     */
    @Override
    public UserVO findUserInfo(Long id) {
        return userDao.findUserInfo(id);
    }

    /**
     * 查询所有用户
     * @param pageNum
     * @param pageSize
     * @param roleId
     * @param keyword
     * @return
     */
    @Override
    public PageInfo<UserVO> findAll(int pageNum, int pageSize, Integer roleId, String keyword){
        PageHelper.startPage(pageNum,pageSize);
        List<UserVO> userList = userDao.findAll(roleId, keyword);
        PageInfo<UserVO> result = new PageInfo<UserVO>(userList);
        return result;
    }

    /**
     *查询所有用户
     * @param roleId
     * @param keyword
     * @return
     */
    @Override
    public List<UserVO> findAll(Integer roleId, String keyword) {
        return userDao.findAll(roleId, keyword);
    }

    /**
     * 根据用户id修改用户信息
     * @param user 用户实体对象
     */
	@Override
	public void updateUser(UserDO user) {
		userDao.updateUser(user);
	}

    /**
     * 根据用户id修改密码
     * @param id
     * @param password
     */
    @Override
    public void updatePassword(Long id, String password) {
        userDao.updatePassword(id,password);
    }

    /**
     * 根据用户id修改用户信息
     * @param user
     * 用户实体对象
     */
    @Override
    public void updateUserInfo(UserDO user) {
        userDao.updateUserInfo(user);
    }

    /**
     * 根据用户id修改用户状态，0表示有效，1表示无效
     * @param id
     */
    @Override
    public void changeStatus(Long id, Integer deleteStatus) {
         userDao.changeStatus(id,deleteStatus);
    }
    /**
     * 修改用户角色为roleId的用户为null
     * @param roleId
     */
    @Override
    public void updateUserRole(Integer roleId) {
         userDao.updateUserRole(roleId);
    }
    /**
     * 删除用户
     * @param id
     */
    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }
}
