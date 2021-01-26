package com.lx.attendance.model.vo;

import java.math.BigDecimal;

/**
 * @author: dantong.xu
 * @date: 2018/10/23
 * @describe: 用户实体类
 */
public class UserVO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String  password;
    /**
     * 联系方式
     */
    private String  tel;
    /**
     * 昵称
     */
    private String  nickname;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 创建时间
     */
    private String  createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 逻辑删除
     */
    private int deleteStatus;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 角色名称 
     */
    private String roleName;
    /**
     * 用户创建类型
     */
    private int type;
    /**
     * 用户头像
     */
    private String photo;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 工资
     */
    private Double wage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getWage() {
        return wage;
    }

    public void setWage(Double wage) {
        this.wage = wage;
    }
}
