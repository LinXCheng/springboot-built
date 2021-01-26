package com.lx.attendance.model.domain;

import java.util.Date;

public class  LeaveDO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 开始日期yyyy-mm-dd
     */
    private Date startTime;
    /**
     * 结束日期yyyy-mm-dd
     */
    private Date endTime;
    /**
     * 请假类型
     */
    private int typeId;
    /**
     * 详细原因
     */
    private String applyReason;
    /**
     * 请假天数
     */
    private int numberDay;
    /**
     * 申请日期
     */
    private Date applyTime;
    /**
     * 审核日期
     */
    private Date auditTime;
    /**
     * 申请用户
     */
    private Long userId;
    /**
     * 审核用户
     */
    private Long auditId;

    /**
     * 审核状态
     */
    private int state;

    /**
     * 申请类型名称
     */
    private String typeName;

    /**
     * 用户昵称
     */
    private String nickName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public int getNumberDay() {
        return numberDay;
    }

    public void setNumberDay(int numberDay) {
        this.numberDay = numberDay;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAuditId() {
        return auditId;
    }

    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
