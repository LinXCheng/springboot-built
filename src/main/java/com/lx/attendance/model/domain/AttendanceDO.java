package com.lx.attendance.model.domain;

import java.util.Date;

public class AttendanceDO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 日期 yyyy-MM-dd 格式
     */
    private Date time;
    /**
     *上班打卡
     */
    private Date morning;
    /**
     * 下班打卡
     */
    private Date afternoon;
    /**
     * 日期类型 0 工作日 1周末 2法定节假日 3调休 4当前时间处于工作日但是经过周末调休
     */
    private int type;

    /**
     * 状态 type:0 正常上下班打卡 1 迟到/早退 2只有一次打卡记录的异常记录 3下班打卡时间跨天 4下班时间跨天并且上班时间是迟到
     */
    private int state;

    /**
     * 用户id（区分数据源）
     */
    private Long userId;

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

    public Date getMorning() {
        return morning;
    }

    public void setMorning(Date morning) {
        this.morning = morning;
    }

    public Date getAfternoon() {
        return afternoon;
    }

    public void setAfternoon(Date afternoon) {
        this.afternoon = afternoon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
}
