package com.lx.attendance.model;

/**
 * 用户实体类
 */
public class UserDTO {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String  password;
   


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
}
