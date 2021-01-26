package com.lx.attendance.model.domain;


public class WageDO {

    private Long id;
    /**
     * 工资所属年月
     */
    private String yearMonth;
    /**
     * 工资
     */
    private double wage;
    /**
     * 请假天数
     */
    private int leaveDay;
    /**
     * 工资所属年月
     */
    private double leaveAmount;
    /**
     * 出差天数
     */
    private int businessDay;
    /**
     * 出差补贴
     */
    private double businessAmount;
    /**
     * 加班天数
     */
    private int overtimeDay;
    /**
     * 加班补贴
     */
    private double overtimeAmount;
    /**
     * 合计工资
     */
    private double totalAmount;
    /**
     * 养老金
     */
    private double pension;
    /**
     * 失业保险
     */
    private double unemploymentBenefits;
    /**
     * 医疗保险
     */
    private double medical;
    /**
     * 住房公积金
     */
    private double housingFund;
    /**
     * 五险一金总计
     */
    private double fiveInsurances;
    /**
     * 税前工资
     */
    private double grossSalary;
    /**
     * 税后工资
     */
    private double taxableCompany;
    /**
     * 税率
     */
    private float rate;
    /**
     * 应税工资
     */
    private double personalIncomeTax;
    /**
     * 实发工资
     */
    private double realWages;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 其他扣款
     */
    private double otherDeduct;
    /**
     * 出勤天数
     */
    private int attendance;
    /**
     * 日薪
     */
    private double dailyWage;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户id备用
     */
    private Long userIdStandby;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public int getLeaveDay() {
        return leaveDay;
    }

    public void setLeaveDay(int leaveDay) {
        this.leaveDay = leaveDay;
    }

    public double getLeaveAmount() {
        return leaveAmount;
    }

    public void setLeaveAmount(double leaveAmount) {
        this.leaveAmount = leaveAmount;
    }

    public int getBusinessDay() {
        return businessDay;
    }

    public void setBusinessDay(int businessDay) {
        this.businessDay = businessDay;
    }

    public double getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(double businessAmount) {
        this.businessAmount = businessAmount;
    }

    public int getOvertimeDay() {
        return overtimeDay;
    }

    public void setOvertimeDay(int overtimeDay) {
        this.overtimeDay = overtimeDay;
    }

    public double getOvertimeAmount() {
        return overtimeAmount;
    }

    public void setOvertimeAmount(double overtimeAmount) {
        this.overtimeAmount = overtimeAmount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getPension() {
        return pension;
    }

    public void setPension(double pension) {
        this.pension = pension;
    }

    public double getUnemploymentBenefits() {
        return unemploymentBenefits;
    }

    public void setUnemploymentBenefits(double unemploymentBenefits) {
        this.unemploymentBenefits = unemploymentBenefits;
    }

    public double getMedical() {
        return medical;
    }

    public void setMedical(double medical) {
        this.medical = medical;
    }

    public double getHousingFund() {
        return housingFund;
    }

    public void setHousingFund(double housingFund) {
        this.housingFund = housingFund;
    }

    public double getFiveInsurances() {
        return fiveInsurances;
    }

    public void setFiveInsurances(double fiveInsurances) {
        this.fiveInsurances = fiveInsurances;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }

    public double getTaxableCompany() {
        return taxableCompany;
    }

    public void setTaxableCompany(double taxableCompany) {
        this.taxableCompany = taxableCompany;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public double getPersonalIncomeTax() {
        return personalIncomeTax;
    }

    public void setPersonalIncomeTax(double personalIncomeTax) {
        this.personalIncomeTax = personalIncomeTax;
    }

    public double getRealWages() {
        return realWages;
    }

    public void setRealWages(double realWages) {
        this.realWages = realWages;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getOtherDeduct() {
        return otherDeduct;
    }

    public void setOtherDeduct(double otherDeduct) {
        this.otherDeduct = otherDeduct;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public double getDailyWage() {
        return dailyWage;
    }

    public void setDailyWage(double dailyWage) {
        this.dailyWage = dailyWage;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getUserIdStandby() {
        return userIdStandby;
    }

    public void setUserIdStandby(Long userIdStandby) {
        this.userIdStandby = userIdStandby;
    }
}
