package com.lx.attendance.controller;

import com.lx.attendance.model.domain.*;
import com.lx.attendance.model.vo.UserVO;
import com.lx.attendance.service.*;
import com.lx.attendance.utils.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.lx.attendance.utils.CommonDate.dateStrToDate;
import static com.lx.attendance.utils.CommonDate.getLastsMonthFirstDay;
import static com.lx.attendance.utils.CommonDate.getTotalDays;

/**
 * 工资
 */
@Controller
public class WageController {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private BusinessTripService businessTripService;
    @Autowired
    private LeaveService leaveService;
    @Autowired
    private UserService userService;
    @Autowired
    private SystemParameterService systemParameter;
    @Autowired
    private WageService wageService;

    @RequestMapping("/wage")
    public ModelAndView wageIndex(String YearMonth, String keyword) {
        ModelAndView modelAndView = new ModelAndView("wage/wage");
        Session session = SecurityUtils.getSubject().getSession();
        String roleCode = (String) session.getAttribute(Constants.SESSION_USER_ROLE);
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        Date systemDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        if (StringUtils.isBlank(YearMonth)) {
            YearMonth = simpleDateFormat.format(getLastsMonthFirstDay(systemDate));
        }
        // 员工权限下
        if ("employees".equals(roleCode)) {
            // 如果查询出所需日期中工资记录是空，那么则计算考勤后得出工资记录存入数据库
            WageDO wageDO = wageService.findWageByUserId(userInfo.getId(), YearMonth);
            if (null == wageDO) {
                wageDO = statisticalWage(userInfo.getId(), YearMonth);
                wageService.insertWage(wageDO);// 新增工资记录
            }
            modelAndView.addObject("wage", wageDO);
        } else { //管理员和超管权限下查询出还未有工资记录的人员，将他们的工资批量新增，然后在查询出来给前台展示
            List<WageDO> wageDOS = wageService.findWage(YearMonth);
            List<WageDO> wageDOS1 = new ArrayList<>();
            for(WageDO wageDO :wageDOS){
                if(null == wageDO.getId()){
                    wageDO = statisticalWage(wageDO.getUserIdStandby(), YearMonth);
                    if(null != wageDO) {
                        wageDOS1.add(wageDO);
                    }
                }
            }
            if(0 != wageDOS1.size()){
                wageService.insertWageBatch(wageDOS1);
            }
            List<WageDO> wageDOS2 = wageService.findAllWage(YearMonth,keyword);
            modelAndView.addObject("wageList", wageDOS2);
        }
        modelAndView.addObject("YearMonth", YearMonth);
        modelAndView.addObject("keyword", keyword);
        return modelAndView;
    }

    /**
     * 计算用户薪酬
     * @param userId
     * @param YearMonth
     * @return
     */
    public WageDO statisticalWage(Long userId, String YearMonth) {
        // 根据日期和用户id查询所有打卡记录
        List<AttendanceDO> attendanceDOList = attendanceService.findAttendanceByTime(YearMonth, userId);
        int Working_days = attendanceDOList.size();//一共打卡几天
        int late_leave = 0;//迟到或者早退几天
        int just_one = 0;//只有一次打卡的异常记录几天
        int working_overtime = 0;//工作日加班几天
        int rest_overtime = 0;//休息日加班几天
        int holiday_overtime = 0;//节假日加班几天
        int leave_day = 0;//请假天数
        int businness_woring = 0;//出差且是工作日的天数
        int businness_rest_day = 0;//出差且是休息日的天数
        int businness_holiday_day = 0;//出差且是节假日的天数
        String sign_out = "20:00:00";//判断工作日加班时间
        Date sign_out_date = dateStrToDate(sign_out, "HH:mm:ss");
        if (0 != attendanceDOList.size()) { // 如果考勤记录不为空则继续则计算工资，遍历每一条数据
            for (AttendanceDO attendanceDO : attendanceDOList) {
                int state = attendanceDO.getState();
                int type = attendanceDO.getType();
                if (0 == type || 3 == type) {//type是工作日的基础上（ 迟到/早退 或者只有一次打卡记录的异常天数需要扣工资的天数）
                    if (1 == state) {
                        late_leave++;//迟到或者早退几天
                        if (null != attendanceDO.getAfternoon()) {// 判断有没有签退记录是在晚上八点后，有的话加班天数加1
                            if (sign_out_date.before(attendanceDO.getAfternoon())) {
                                working_overtime++;
                            }
                        }
                    } else if (2 == state) {// 只有一次打卡记录的异常天数需要扣工资的天数
                        just_one++;//只有一次打卡的异常记录+1
                        if (null != attendanceDO.getAfternoon()) {
                            if (sign_out_date.before(attendanceDO.getAfternoon())) {
                                working_overtime++;
                            }
                        }
                    } else if (3 == state) { // state是3 则是正常上下班，且需要计算加班
                        working_overtime++;
                    } else if (4 == state) {// state是4 则是上班迟到，但是加班了
                        working_overtime++;
                        late_leave++;
                    }
                } else if (1 == type || 4 == type) {//休息日上班就需要额外的补贴的天数
                    rest_overtime++;
                } else if (2 == type) {//节假日加班除了正常工资外额外补贴天数
                    holiday_overtime++;
                }
            }
            // 查询请假记录和出差记录
            List<LeaveDO> leaveDOS = leaveService.findLeaveByDateYM(userId, YearMonth);
            List<BusinessTripDO> businessTripDOS = businessTripService.findBusinessTripByDateYM(userId, YearMonth);
            if (0 != leaveDOS.size()) {
                for (LeaveDO leaveDO : leaveDOS) {
                    int type_id = leaveDO.getTypeId();
                    int number_day = leaveDO.getNumberDay();
                    if (3 == type_id || 4 == type_id) {//休假和调休不扣工资，还需往考勤天数上加
                        Working_days = Working_days + number_day;
                    } else {
                        leave_day = leave_day + number_day;//事假与病假扣工资
                    }
                }
            }
            if (0 != businessTripDOS.size()) {
                for (BusinessTripDO businessTripDO : businessTripDOS) {
                    int working = businessTripDO.getWorkingDays();
                    int rest = businessTripDO.getRestDays();
                    int holiday = businessTripDO.getHolidayDays();
                    if (0 != working) {//出差每一天都算工资，并且还要独立出工作日休息日和节假日，计算额外补贴
                        Working_days = Working_days + working;
                        businness_woring = businness_woring + working;
                    }
                    if (0 != rest) {
                        Working_days = Working_days + rest;
                        businness_rest_day = businness_rest_day + rest;
                    }
                    if (0 != holiday) {
                        Working_days = Working_days + holiday;
                        businness_holiday_day = businness_holiday_day + holiday;
                    }
                }
            }
            UserVO userInfo = userService.findUserInfo(userId);
            Double wage = userInfo.getWage();//基本工资
            int month_day = getTotalDays(dateStrToDate(YearMonth+"-01"));//工资所属月的天数
            int month_working_day = 0;
            // 判断当月是31还是30天还是其他，所需工资的天数分别是23、22、20
            if(31 == month_day){
                month_working_day = 23;
            } else if (30 == month_day){
                month_working_day = 22;
            } else {
                month_working_day = 20;
            }
            Double day_wage = wage / month_working_day;//日薪
            WageDO wageDO = new WageDO();
            wageDO.setAttendance(Working_days);//存入考勤天数
            wageDO.setDailyWage(day_wage);//存入日薪
            wageDO.setYearMonth(YearMonth);//存入工资所属月
            wageDO.setWage(wage);//存入工资
            wageDO.setLeaveDay(leave_day);//存入请假天数
            wageDO.setLeaveAmount(day_wage * leave_day);//存入请假扣款
            // 查询扣款和各种补贴的参数
            List<SystemParameterDO> systemParameterDOS = systemParameter.findSystemParameter();
            double overtime_woring_subsidies = 0.0;// 工作日加班补贴金额
            double overtime_rest_subsidies = 0.0;// 休息日加班补贴金额
            double overtime_holiday_subsidies = 0.0;// 节假日加班补贴金额
            double business_woring_subsidies = 0.0;// 工作日出差补贴金额
            double business_rest_day_subsidies = 0.0;// 休息日出差补贴金额
            double business_holiday_day_subsidies = 0.0;// 节假日加班补贴金额
            double late_leave_deduct = 0;//迟到或者早退几天
            double just_one_deduct = 0;//只有一次打卡的异常记录几天
            //遍历查询出的补贴参数，将对应的金额存入对应的参数中
            for (SystemParameterDO systemParameterDO : systemParameterDOS) {
                int type = systemParameterDO.getType();
                double money = systemParameterDO.getMoney();
                int wage_flag = systemParameterDO.getWage();
                if (1 == type) {
                    if (1 == wage_flag) {
                        overtime_woring_subsidies = money;
                    } else if (2 == wage_flag) {
                        overtime_rest_subsidies = money;
                    } else if (3 == wage_flag) {
                        overtime_holiday_subsidies = money;
                    }
                } else if (2 == type) {
                    if (1 == wage_flag) {
                        business_woring_subsidies = money;
                    } else if (2 == wage_flag) {
                        business_rest_day_subsidies = money;
                    } else if (3 == wage_flag) {
                        business_holiday_day_subsidies = money;
                    }
                } else if (3 == type) {
                    late_leave_deduct = money;
                } else if (4 == type) {
                    just_one_deduct = money;
                }
            }
            // 一共出差多少天
            wageDO.setBusinessDay(businness_woring + businness_rest_day + businness_holiday_day);
            // 一共出差的补贴
            double BusinessAmount = (businness_woring * business_woring_subsidies) + (businness_rest_day * business_rest_day_subsidies) + (businness_holiday_day * business_holiday_day_subsidies);
            wageDO.setBusinessAmount(BusinessAmount);
            // 一共加班多少天
            wageDO.setOvertimeDay(working_overtime + rest_overtime + holiday_overtime);
            // 一共加班的补贴
            double OvertimeAmount = (working_overtime * overtime_woring_subsidies) + (rest_overtime * overtime_rest_subsidies) + (holiday_overtime * overtime_holiday_subsidies);
            wageDO.setOvertimeAmount(OvertimeAmount);
            // 考勤异常的扣款
            double OtherDeduct = (late_leave_deduct * late_leave) + (just_one_deduct * just_one);
            wageDO.setOtherDeduct(OtherDeduct);
            // 总共的工资金额
            double TotalAmount = (Working_days * day_wage) + BusinessAmount + OvertimeAmount - OtherDeduct;
            wageDO.setTotalAmount(TotalAmount);
            /*
            * 一下是五险一金的计算公式和个人所得税计算公式，五险一金全部按照最高标准
            * */
            double Pension = wage * 0.08;
            wageDO.setPension(Pension);
            double UnemploymentBenefits = wage * 0.005;
            wageDO.setUnemploymentBenefits(UnemploymentBenefits);
            double Medical = wage * 0.02;
            wageDO.setMedical(Medical);
            double HousingFund = wage * 0.12;
            wageDO.setHousingFund(HousingFund);
            double FiveInsurances = Pension + UnemploymentBenefits + Medical + HousingFund;
            wageDO.setFiveInsurances(FiveInsurances);
            double GrossSalary = TotalAmount - FiveInsurances;
            wageDO.setGrossSalary(GrossSalary);
            double TaxableCompany = GrossSalary - 5000.00;
            wageDO.setTaxableCompany(TaxableCompany);
            wageDO.setRate(0.03f);
            double setPersonalIncomeTax = TaxableCompany * 0.03;
            wageDO.setPersonalIncomeTax(setPersonalIncomeTax);
            double realWages = GrossSalary - setPersonalIncomeTax;
            wageDO.setRealWages(realWages);
            wageDO.setUserId(userId);
            return wageDO;
        } else {
            return null;
        }
    }
}
