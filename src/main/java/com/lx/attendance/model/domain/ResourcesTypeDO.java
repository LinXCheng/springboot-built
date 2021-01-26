package com.lx.attendance.model.domain;

public class ResourcesTypeDO {

	 /**
     *id
     */
    private Integer id;
    /**
     *权限类型名称
     */
    private String name;
    /**
     * 权限类型编码
     */
    private String code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
