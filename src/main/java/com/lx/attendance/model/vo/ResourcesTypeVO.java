package com.lx.attendance.model.vo;

public class ResourcesTypeVO {

    /**
     *资源类型id
     */
    private Integer id;
    /**
     *资源类型名称
     */
    private String name;
    /**
     *资源类型编码
     */
    private String code;

    /**
     *资源id
     */
    private Long resourcesId;

    /**
     * 资源摘要
     * */
    private String description;

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

    public Long getResourcesId() {
        return resourcesId;
    }

    public void setResourcesId(Long resourcesId) {
        this.resourcesId = resourcesId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
