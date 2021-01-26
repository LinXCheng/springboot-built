package com.lx.attendance.model.domain;

public class SystemParameterDO {
    /**
     * id
     */
    private int id;
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数金额
     */
    private double money;
    /**
     * 标识工作日节假日休息日
     */
    private int wage;
    /**
     * 类型
     */
    private int type;

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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
