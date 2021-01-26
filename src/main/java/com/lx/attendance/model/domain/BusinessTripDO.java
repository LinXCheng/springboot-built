package com.lx.attendance.model.domain;

import java.util.Date;

public class BusinessTripDO {
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
     * 工作日
     */
    private int workingDays;
    /**
     * 周末
     */
    private int restDays;
    /**
     * 假期
     */
    private int holidayDays;
    /**
     * 详细原因
     */
    private String applyReason;
    /**
     * 申请日期
     */
    private Date applyTime;
    /**
     * 审核日期
     */
    private Date auditTime;
    /**
     * 审核状态
     */
    private int state;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String nickName;

    private int numberDay;

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

    public int getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(int workingDays) {
        this.workingDays = workingDays;
    }

    public int getRestDays() {
        return restDays;
    }

    public void setRestDays(int restDays) {
        this.restDays = restDays;
    }

    public int getHolidayDays() {
        return holidayDays;
    }

    public void setHolidayDays(int holidayDays) {
        this.holidayDays = holidayDays;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getNumberDay() {
        return numberDay;
    }

    public void setNumberDay(int numberDay) {
        this.numberDay = numberDay;
    }
}
