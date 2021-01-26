package com.lx.attendance.model.domain;

public class LeaveTypeDO {
    /**
     * 请假类型id
     */
    private int id;
    /**
     * 请假名称
     */
    private String name;

    /**
     * 请假天数
     */
    private int numberDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberDay() {
        return numberDay;
    }

    public void setNumberDay(int numberDay) {
        this.numberDay = numberDay;
    }
}
