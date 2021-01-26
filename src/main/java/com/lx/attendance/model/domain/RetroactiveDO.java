package com.lx.attendance.model.domain;

import java.util.Date;

public class RetroactiveDO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 日期 yyyy-MM-dd 格式
     */
    private Date time;
    /**
     * 状态 type:0 正常上下班打卡 1 迟到/早退 2只有一次打卡记录的异常记录 3下班打卡时间跨天 4下班时间跨天并且上班时间是迟到
     */
    private int state;

    /**
     * 用户id（区分数据源）
     */
    private Long userId;
    /**
     * 申请日期
     */
    private Date applyTime;

    /**
     * 审批日期
     */
    private Date auditTime;

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
