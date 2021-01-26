package com.lx.attendance.controller;

import com.github.pagehelper.PageInfo;
import com.lx.attendance.model.domain.*;
import com.lx.attendance.service.*;
import com.lx.attendance.utils.ResultSet;
import com.lx.attendance.utils.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

import static com.lx.attendance.utils.CommonDate.*;
import static com.lx.attendance.utils.CommonDate.dateStrToDate;
import static com.lx.attendance.utils.CommonDate.weekNumber;
import static com.lx.attendance.utils.ControllerResult.error;
import static com.lx.attendance.utils.ControllerResult.success;
import static com.lx.attendance.utils.logControl.logPrint;

@Controller /*标注为控制层，只有存在该注解才可以访问数据库*/
@RequestMapping("/attendance") /*前台访问的当前控制层的总路由*/
public class AttendanceController {

    @Autowired/* 将下列的注入到ioc容器中才可以对数据库进行操作*/
    private HolidayService holidayService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private LeaveTypeService leaveTypeService;

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private BusinessTripService businessTripService;

    /**
     * 签到页面
     *
     * @return
     */
    @RequestMapping("/signIn")
    public ModelAndView signIn() {
        // 返回/web/attendance文件下的sign_in.ftl页面
        ModelAndView model = new ModelAndView("attendance/sign_in");
        // 定义系统时间
        Date systemDate = new Date();
        // 定义需要的日期/时间格式根据括号内的
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        // 转换成字符串格式根据上面定义的格式（这里是HH：mm:ss）
        String systemDateStr = simpleDateFormat.format(systemDate);
        // 获取session对象
        Session session = SecurityUtils.getSubject().getSession();
        // 获取存在session中的用户信息
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        // 根据时间和用户名查询是否有签到记录
        AttendanceDO attendance = attendanceService.findSignIn(systemDate, userInfo.getId());
        String sign_special = "06:00:00";//最早打卡上午时间
        String sign_out = "18:00:00";//打卡下午时间
        /*将定义的字符串转换成括号内的格式，这里是HH：mm：ss,（默认是1970年01月01号）*/
        Date sign_special_date = dateStrToDate(sign_special, "HH:mm:ss");
        Date sign_out_date = dateStrToDate(sign_out, "HH:mm:ss");
        Date system = dateStrToDate(systemDateStr, "HH:mm:ss");
        // 往model中存储 type 这个数据 是为了判断 是0 签到、1 签退、2 已签到、3 请假中、4 出差中
        // 判断是否当前时间存在打卡记录，有打卡记录表示肯定有上班签到
        if (null != attendance) {
            // 如果存在签退记录，则 往type存储2,前端页面会对应的展示已签到
            if (null != attendance.getAfternoon()) {
                model.addObject("type", 2);
            } else {
                // 如果存在签退记录，则 往type存储1表示下班签退
                model.addObject("type", 1);
            }
        } else { //如果当天不存在签到记录
            // 查询当前时间是否处于请假中
            LeaveDO leaveDO = leaveService.findLeaveBySystemDate(userInfo.getId(), systemDate);
            // 查询当前时间是否处于出差中
            BusinessTripDO businessTripDO = businessTripService.findBusinessTripBySystemDate(userInfo.getId(), systemDate);
            // 如果今日没有打卡记录只有在下班时间到之后才可以签退（否则只能经过签到然后在签退）
            if (system.after(sign_out_date)) {
                model.addObject("type", 1);
            } else {// 否则就是06:00:00-18:00:00之间都是签到
                model.addObject("type", 0);
            }
            // 如果查询请假当前时间是否处于请假中不为空，表示在请假，则页面显示请假中
            if (null != leaveDO) {
                model.addObject("type", 3);
            }
            // 如果查询出差当前时间是否处于出差中不为空，表示在出差，则页面显示出差中
            if (null != businessTripDO) {
                model.addObject("type", 4);
            }
            // 如果是特殊的处于06:00:00之前那么需要查询前一天是否有签退记录，如果有则签到完成，没有则需要签退
            if (system.before(sign_special_date)) {
                // 获取系统时间前一天
                Date beforeDate = dateAddDayToDate(systemDate, -1);
                /*查询系统前一天有没有请假记录，有没有出差记录，有则显示对应的，
                没有的话查询前一天有没有打卡记录，如果有，判断是否有签退记录，有的话展示已签到，
                没有则表示下班签退*/
                leaveDO = leaveService.findLeaveBySystemDate(userInfo.getId(), beforeDate);
                businessTripDO = businessTripService.findBusinessTripBySystemDate(userInfo.getId(), beforeDate);
                attendance = attendanceService.findSignIn(beforeDate, userInfo.getId());
                if (attendance != null) {
                    if (null != attendance.getAfternoon()) {
                        model.addObject("type", 2);
                    } else {
                        model.addObject("type", 1);
                    }
                } else {
                    model.addObject("type", 1);
                }
                if (null != leaveDO) {
                    model.addObject("type", 3);
                }
                if (null != businessTripDO) {
                    model.addObject("type", 4);
                }
            }
        }
        // 查询所有正常出勤的记录
        List<AttendanceDO> attendanceDOList = attendanceService.findAttendanceByUserId(userInfo.getId(), 0);
        // 查询所有非正常出勤的记录
        List<AttendanceDO> attendanceDOS = attendanceService.findAttendanceByUserId(userInfo.getId(), 1);
        // 查询所有请假类型对应的本月的所有记录，在页面展示
        List<LeaveTypeDO> leaveTypeDOS = leaveTypeService.findLeaveBySystem(userInfo.getId());
        /*存储到容器中，在页面上使用*/
        model.addObject("leaveTypeDOS", leaveTypeDOS);
        model.addObject("zcDate", attendanceDOList);
        model.addObject("qqDate", attendanceDOS);
        return model;
    }

    /**
     * 签到保存
     *
     * @param type
     * @return
     */
    @RequestMapping("/saveSignIn")
    @ResponseBody
    @Transactional(noRollbackFor = {RuntimeException.class, Exception.class})
    public ResultSet saveSignIn(int type) {
        // 创建实体类存储数据
        AttendanceDO attendanceDO = new AttendanceDO();
        // 获取系统当前时间
        Date systemDate = new Date();
        // 格式化后的日期值字符串
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String systemDateStr = simpleDateFormat.format(systemDate);
        // 将系统日期转化为yyyy-mm-dd的日期格式
        String sign_in = "09:00:00";//打卡早上时间
        String sign_out = "18:00:00";//打卡下午时间
        String sign_special = "06:00:00";//打卡下班异常时间节点
        // 转换为对应的日期格式
        Date sign_in_date = dateStrToDate(sign_in, "HH:mm:ss");
        Date sign_out_date = dateStrToDate(sign_out, "HH:mm:ss");
        Date sign_special_date = dateStrToDate(sign_special, "HH:mm:ss");
        Date sign_in_system = dateStrToDate(systemDateStr, "HH:mm:ss");
        /**
         * 获取存在session对象中，当前登录的对象的用户信息
         */
        Session session = SecurityUtils.getSubject().getSession();
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        attendanceDO.setUserId(userInfo.getId());// 存储用户id，判别是哪个用户的签到记录（用于区分数据）
        attendanceDO.setTime(new Date());//存储月份（数据库定义的是yyyy-mm-dd类型的，会自动截取系统时间中的yyyy-mm-dd格式）
        try {
            // 0 表示上班签到，那么绝对是新增数据
            if (0 == type) {
                // 将当前打卡时间存入数据库（数据库格式是时分秒，会自动格式化时间，所以只需要存入当前系统时间）
                attendanceDO.setMorning(systemDate);
                /**
                 * 确定当前日期是什么类型（type:0 工作日 1周末 2法定节假日 3调休 4当前时间处于工作日但是经过周末调休）
                 * 返回实体类
                 * */
                attendanceDO = DetermineDate(attendanceDO, systemDate);
                /*
                * after 和 before  表示 大于 和 小于
                * */
                // 如果默认上班时间09:00:00大于系统时间时间（type:0 正常上下班打卡 1 迟到/早退 2只有一次打卡记录的异常记录 3下班打卡时间跨天 4下班时间跨天并且上班时间是迟到）
                if (sign_in_date.after(sign_in_system)) {
                    attendanceDO.setState(2);
                } else {
                    attendanceDO.setState(1);
                }
                // 新增打卡数据
                attendanceService.insertAttendance(attendanceDO);
            } else { // 否则就是 下班签退，那么则有可能是新增数据，也有可能是修改数据，需要具体判断
                // 如果下班时间大于06：00：00 表示当前下班打卡没有跨天
                if (sign_special_date.before(sign_in_system)) {
                    AttendanceDO attendance = attendanceService.findSignIn(systemDate, userInfo.getId());
                    if (null != attendance) {
                        attendance.setAfternoon(systemDate);
                        if (attendance.getMorning() != null) {
                            // 如果已存在的上班打卡时间是小于标准上班时间并且当前时间大于标准下班时间 说明没有迟到
                            if (attendance.getMorning().before(sign_in_date) && sign_in_system.after(sign_out_date)) {
                                attendance.setState(0);
                            } else {
                                attendance.setState(1);
                            }
                        } else {// 如果上班打卡没有记录则不管是否准时打卡下班 都是异常记录
                            attendance.setState(2);
                        }
                        attendanceService.updateSignOut(attendance);
                    } else {
                        attendanceDO.setAfternoon(systemDate);
                        attendanceDO = DetermineDate(attendanceDO, systemDate);
                        // 如果默认下班时间18:00:00小于系统时间时间（type:0 正常上下班打卡 1 迟到/早退 2只有一次打卡记录的异常记录 3下班打卡时间跨天 4下班时间跨天并且上班时间是迟到）
                        if (sign_out_date.before(sign_in_system)) {
                            attendanceDO.setState(2);
                        } else {
                            attendanceDO.setState(1);
                        }
                        attendanceService.insertAttendance(attendanceDO);
                    }
                } else {
                    Date beforeDate = dateAddDayToDate(systemDate, -1);
                    AttendanceDO attendance = attendanceService.findSignIn(beforeDate, userInfo.getId());
                    if (null != attendance) {
                        attendance.setAfternoon(systemDate);
                        if (attendance.getMorning() != null) {
                            // 如果已存在的上班打卡时间是小于标准上班时间并且当前时间大于标准下班时间 说明没有迟到
                            if (attendance.getMorning().before(sign_in_date)) {
                                attendance.setState(3);
                            } else {
                                attendance.setState(4);
                            }
                        } else {// 如果上班打卡没有记录则不管是否准时打卡下班 都是异常记录
                            attendance.setState(2);
                        }
                        attendanceService.updateSignOut(attendance);
                    } else {
                        attendanceDO.setTime(beforeDate);
                        attendanceDO.setAfternoon(systemDate);
                        attendanceDO = DetermineDate(attendanceDO, beforeDate);
                        // 如果默认下班时间18:00:00小于系统时间时间（type:0 正常上下班打卡 1 迟到/早退 2只有一次打卡记录的异常记录 3下班打卡时间跨天 4下班时间跨天并且上班时间是迟到）
                        attendanceDO.setState(2);
                        attendanceService.insertAttendance(attendanceDO);
                    }
                }
            }
            return success();
        } catch (Exception e) {
            logPrint(AttendanceController.class, null, e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error("打卡失败!");
        }
    }

    /**
     * 确定当前日期是什么类型（type:0 工作日 1周末 2法定节假日 3调休 4当前时间处于工作日但是经过周末调休）
     *
     * @param attendanceDO 考勤实体类
     * @param date         yyyy-mm-dd格式的日期
     * @return
     */
    public AttendanceDO DetermineDate(AttendanceDO attendanceDO, Date date) {
        // 判断当前打开上班的日期是否是节假日
        HolidayDO holidayDO = holidayService.findHolidayByDate(date);
        if (holidayDO != null) {
            // 如果查询出来wage是1的话则为工作日调休,type:0 工作日 1周末 2法定节假日 3工作日调休 4当前时间处于工作日但是经过周末调休
            if (1 == holidayDO.getWage()) {
                attendanceDO.setType(3);
            } else if (2 == holidayDO.getWage()) {// 2当前时间处于工作日但是经过周末调休
                attendanceDO.setType(4);
            } else {// 否则都为节假日
                attendanceDO.setType(2);
            }
        } else {
            // 判断一个日期是周几
            int number = weekNumber(date);
            if (6 == number || 7 == number) {
                attendanceDO.setType(1);
            } else {
                attendanceDO.setType(0);
            }
        }
        return attendanceDO;
    }

    /**
     * 管理员和员工查看对应的请假记录
     *
     * @param pageNum
     * @param pageSize
     * @param dateInterval
     * @param keyword
     * @return
     */
    @RequestMapping("/leave")
    @ResponseBody
    @Transactional(noRollbackFor = {RuntimeException.class, Exception.class})
    public ModelAndView leave(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "7") int pageSize, String dateInterval, String keyword) {
        ModelAndView model = new ModelAndView("attendance/leave_list");
        /**
         * 从session获取用户信息和用户角色，用户角色是为了，不同角色呈现不同内容
         */
        Session session = SecurityUtils.getSubject().getSession();
        String roleCode = (String) session.getAttribute(Constants.SESSION_USER_ROLE);
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        Date startTime = null;
        Date endTime = null;
        // 判断前台传来的时间区间是否为空（yyyy-mm-dd~yyyy-mm-dd），不为空的话使用split方法切割，根据“~”切割成数组
        if (StringUtils.isNotBlank(dateInterval)) {
            String startTimeStr = dateInterval.split("~")[0];//获取数组第一个元素就是起始时间
            String endTimeStr = dateInterval.split("~")[1];// 第二个元素是结束时间
            startTime = dateStrToDate(startTimeStr); // 将字符串装换成日期格式
            endTime = dateStrToDate(endTimeStr);// 将字符串装换成日期格式
        }
        // 判断权限是员工
        if ("employees".equals(roleCode)) {
            // 查询此员工的所有请假记录，存入容器给予前台
            PageInfo<LeaveDO> leaveDOS = leaveService.findLeaveByUserId(pageNum, pageSize, userInfo.getId(), startTime, endTime);
            model.addObject("leaveDOS", leaveDOS);
        } else { // 判断权限是超级管理员或者管理员
            // 查询所有员工的请假记录，交由管理员和超管审批
            PageInfo<LeaveDO> leaveDOS = leaveService.findAllLeave(pageNum, pageSize, startTime, endTime, keyword);
            model.addObject("leaveDOS", leaveDOS);
        }
        // 回显查询条件中的时间区间
        model.addObject("dateInterval", dateInterval);
        // 回显查询条件中的查询姓名
        model.addObject("keyword", keyword);
        Date systemDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String systemYM = simpleDateFormat.format(systemDate);
        // 传给前台系统的年月，作为管理员审核的判断条件（只能审核当月的数据）
        model.addObject("systemYM", systemYM);
        return model;
    }

    /**
     * 请假页面初始化与查看
     *
     * @param id
     * @return
     */
    @RequestMapping("/leaveEdit")
    public ModelAndView leaveEdit(Long id) {
        ModelAndView model = new ModelAndView("attendance/leave_edit");
        Date systemDate = new Date();
        // 将字符串装换成日期，给前台的时间插件做最小值可选，就是请假不能低于当前的系统时间
        Date minDate = dateStrToDate(strDateToTime(systemDate));
        model.addObject("minDate", minDate);
        // 查询所有的请假类型，给予使用者选择
        List<LeaveTypeDO> leaveType = leaveTypeService.findLeaveType();
        model.addObject("leaveTypeList", leaveType);
        // 如果id 不为空，则是修改，否则的话肯定是新增
        if (null != id) {
            LeaveDO leaveDO = leaveService.findLeaveById(id);
            model.addObject("leave", leaveDO);
        }
        return model;
    }

    /**
     * 用户申请请假记录保存
     *
     * @param leaveDO
     * @param startTimeStr
     * @param endTimeStr
     * @return
     */
    @RequestMapping("/leaveSave")
    @ResponseBody
    @Transactional(noRollbackFor = {RuntimeException.class, Exception.class})
    public ResultSet leaveSave(LeaveDO leaveDO, String startTimeStr, String endTimeStr) {
        /**
         * 获取存在session对象中，当前登录的对象，从中获取用户id
         */
        Session session = SecurityUtils.getSubject().getSession();
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        // 截取开始时间中的年
        String startY = startTimeStr.substring(0, 4);
        int numberDay = 0;// 定义这个变量，后续会拿来存储 请假日期开始到结束提出休息日和节假日后的工作时间当做请假时间
        String startYM = startTimeStr.substring(0, 7);// 开始时间中截取年月
        String endYM = endTimeStr.substring(0, 7);// 结束时间中截取年月
        leaveDO.setStartTime(dateStrToDate(startTimeStr)); // 存入请假的实体类中
        leaveDO.setEndTime(dateStrToDate(endTimeStr));
        leaveDO.setState(0);// 状态肯定为 未通过  state：0未通过 1通过 2驳回
        try {
            if (startYM.equals(endYM)) {// 前台已经做过判断是否是同年同月，这里为了安全在做一次判断，系统中的请假必须同年同月
                // 存储所有的节假日到map中方便查询，key是日期（yyyy-mm-dd），value是实体
                Map<String, HolidayDO> holidayDOMap = holidayService.findThisYearHolidayByDate(startY);
                //截取开始日期中的天和结束日期中的天，遍历这些个日期是否存在是节假日
                int startD = Integer.valueOf(startTimeStr.substring(8, startTimeStr.length()));
                int endD = Integer.valueOf(endTimeStr.substring(8, endTimeStr.length()));
                for (; startD <= endD; startD++) {
                    // 查询map中是否存在这个日期的key，如果存在就有记录，则继续判断是否wage是1，如果是1则是周末调休工作日，天数++
                    if (holidayDOMap.containsKey((startYM + "-" + startD))) {
                        if (holidayDOMap.get((startYM + "-" + startD)).getWage() == 1) {
                            numberDay++;
                        }
                    } else {// 不存在在判断是否是周末 不是周末的话天数++
                        int weekNumber = weekNumber(dateStrToDate((startYM + "-" + startD)));
                        if (6 != weekNumber && 7 != weekNumber) {
                            numberDay++;
                        }
                    }
                }
                leaveDO.setNumberDay(numberDay);// 存入剔除节假日和工作日后的请假天数
                if (null != leaveDO.getId()) { // 判断id是否存在，存在则修改那条存在的数据，不在则新增
                    leaveService.updateLeaveById(leaveDO);
                } else {
                    // 判断起始时间和结束时间是否处于相同月份，如果是处于相同月份则判断当前请假区间是否有节假日和双休日
                    leaveDO.setUserId(userInfo.getId());
                    leaveService.insertLeave(leaveDO);
                }
                return success();//返回成功信息给前台
            } else {
                return error("开始日期和结束日期必须同年同月,如果跨年或者跨月请分别分段申请!");
            }
        } catch (NumberFormatException e) {
            logPrint(AttendanceController.class, leaveDO.getId(), e.getMessage());// 日志统一处理方法
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 异常后数据回滚方法
            return error("申请失败,请重试!");//返回失败消息给前台显示
        }
    }

    /**
     * 管理员审批请假记录，只需要修改记录的状态
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping("/updateState")
    @ResponseBody
    public ResultSet updateState(Long id, Integer type) {
        if (null != id && null != type) {// id表示那条记录的id，type表示是需要 审批通过还是撤销还是驳回 1 点击通过 2 撤销操作 3 点击驳回
            Session session = SecurityUtils.getSubject().getSession();
            UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
            leaveService.updateStateById(id, type, userInfo.getId());
            return success();
        } else {
            return error();
        }
    }

    /**
     * 删除请假记录
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    @Transactional(noRollbackFor = {RuntimeException.class, Exception.class})// 定义事务回滚的条件，这里是运行异常和所有异常
    public ResultSet delete(Long id) {
        try {
            if (null != id) {
                leaveService.deleteLeave(id);// 获取页面传来的删除记录的id，数据库删除
                return success();
            } else {
                return error();
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();// 异常后事务回滚，就是修改后的数据回滚
            logPrint(AttendanceController.class, id, e.getMessage());//日志统一处理
            return error();
        }
    }

    /**
     * 用户和管理员查看出差记录（可参照请假记录，两者类似）
     *
     * @param pageNum
     * @param pageSize
     * @param dateInterval
     * @param keyword
     * @return
     */
    @RequestMapping("/businessTrip")
    @ResponseBody
    @Transactional(noRollbackFor = {RuntimeException.class, Exception.class})
    public ModelAndView businessTrip(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                     @RequestParam(name = "pageSize", required = false, defaultValue = "7") int pageSize, String dateInterval, String keyword) {
        ModelAndView model = new ModelAndView("attendance/business_trip_list");
        Session session = SecurityUtils.getSubject().getSession();
        String roleCode = (String) session.getAttribute(Constants.SESSION_USER_ROLE);
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        Date startTime = null;
        Date endTime = null;
        if (StringUtils.isNotBlank(dateInterval)) {
            String startTimeStr = dateInterval.split("~")[0];
            String endTimeStr = dateInterval.split("~")[1];
            startTime = dateStrToDate(startTimeStr);
            endTime = dateStrToDate(endTimeStr);
        }
        if ("employees".equals(roleCode)) {
            PageInfo<BusinessTripDO> businessTripDOS = businessTripService.findBusinessTripByUserId(pageNum, pageSize, userInfo.getId(), startTime, endTime);
            model.addObject("businessTripDOS", businessTripDOS);
        } else {
            PageInfo<BusinessTripDO> businessTripDOS = businessTripService.findBusinessTrip(pageNum, pageSize, startTime, endTime, keyword);
            model.addObject("businessTripDOS", businessTripDOS);
        }
        model.addObject("dateInterval", dateInterval);
        model.addObject("keyword", keyword);
        Date systemDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String systemYM = simpleDateFormat.format(systemDate);
        model.addObject("systemYM", systemYM);
        return model;
    }

    /**
     * 申请出差页面初始化与查看（可参照申请请假页面，两者类似）
     *
     * @param id
     * @return
     */
    @RequestMapping("/businessTripEdit")
    public ModelAndView businessTripEdit(Long id) {
        ModelAndView model = new ModelAndView("attendance/business_trip_edit");
        Date systemDate = new Date();
        Date minDate = dateStrToDate(strDateToTime(systemDate));
        model.addObject("minDate", minDate);
        if (null != id) {
            BusinessTripDO businessTripDO = businessTripService.findBusinessTripById(id);
            model.addObject("businessTripDO", businessTripDO);
        }
        return model;
    }

    /**
     * 用户申请出差记录保存
     *
     * @param businessTripDO
     * @param startTimeStr
     * @param endTimeStr
     * @return
     */
    @RequestMapping("/businessTripSave")
    @ResponseBody
    @Transactional(noRollbackFor = {RuntimeException.class, Exception.class})
    public ResultSet businessTripSave(BusinessTripDO businessTripDO, String startTimeStr, String endTimeStr) {
        /**
         * 获取存在session对象中，当前登录的对象，从中获取用户id
         */
        Session session = SecurityUtils.getSubject().getSession();
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        String startY = startTimeStr.substring(0, 4);
        int working_days = 0;
        int rest_days = 0;
        int holiday_days = 0;
        String startYM = startTimeStr.substring(0, 7);
        String endYM = endTimeStr.substring(0, 7);
        businessTripDO.setStartTime(dateStrToDate(startTimeStr));
        businessTripDO.setEndTime(dateStrToDate(endTimeStr));
        try {
            if (startYM.equals(endYM)) {
                Map<String, HolidayDO> holidayDOMap = holidayService.findThisYearHolidayByDate(startY);
                int startD = Integer.valueOf(startTimeStr.substring(8, startTimeStr.length()));
                int endD = Integer.valueOf(endTimeStr.substring(8, endTimeStr.length()));
                for (; startD <= endD; startD++) {// 以上部分参照请假保存方法
                    if (holidayDOMap.containsKey((startYM + "-" + startD))) {
                        if (holidayDOMap.get((startYM + "-" + startD)).getWage() == 1) {//如果map中查询出当前日期存在并且，wage是1的话则表示是是工作时间，代表工作日的天数+1
                            working_days++;
                        } else if (2 == holidayDOMap.get((startYM + "-" + startD)).getWage()) {
                            rest_days++;//如果map中查询出当前日期存在并且，wage是2的话则表示是是休息时间，代表休息日的天数+1
                        } else {//如果map中查询出当前日期存在并且，wage是3的话则表示是是节假日时间，代表节假日的天数+1
                            holiday_days++;
                        }
                    } else { // 如果节假日不存在记录，那么只需要判断是否是周末和工作日，然后对应的天数加1
                        int weekNumber = weekNumber(dateStrToDate((startYM + "-" + startD)));
                        if (6 != weekNumber && 7 != weekNumber) {
                            working_days++;
                        } else {
                            rest_days++;
                        }
                    }
                }
                businessTripDO.setWorkingDays(working_days);
                businessTripDO.setRestDays(rest_days);
                businessTripDO.setHolidayDays(holiday_days);
                if (null != businessTripDO.getId()) {
                    businessTripService.updateBusinessTripById(businessTripDO);
                } else {
                    // 判断起始时间和结束时间是否处于相同月份，如果是处于相同月份则判断当前请假区间是否有节假日和双休日
                    businessTripDO.setUserId(userInfo.getId());
                    businessTripService.insertBusinessTrip(businessTripDO);
                }
                return success();
            } else {
                return error("开始日期和结束日期必须同年同月,如果跨年或者跨月请分别分段申请!");
            }
        } catch (NumberFormatException e) {
            logPrint(AttendanceController.class, businessTripDO.getId(), e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return error("申请失败,请重试!");
        }
    }

    /**
     * 管理员审核出差记录（参照请假审核）
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping("/updateBusinessState")
    @ResponseBody
    public ResultSet updateBusinessState(Long id, Integer type) {
        if (null != id && null != type) {
            Session session = SecurityUtils.getSubject().getSession();
            UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
            businessTripService.updateBusinessTripState(id, type, userInfo.getId());
            return success();
        } else {
            return error();
        }
    }

    /**
     * 删除出差记录（参照请假删除）
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteBusinessTrip")
    @ResponseBody
    @Transactional(noRollbackFor = {RuntimeException.class, Exception.class})
    public ResultSet deleteBusinessTrip(Long id) {
        try {
            if (null != id) {
                businessTripService.deleteBusinessTrip(id);
                return success();
            } else {
                return error();
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logPrint(AttendanceController.class, id, e.getMessage());
            return error();
        }
    }

    /**
     * 补卡页面数据初始化
     *
     * @return
     */
    @RequestMapping("/retroactive")
    public ModelAndView retroactive() {
        /**
         * 获取存在session对象中，当前登录的对象，从中获取用户id
         */
        Session session = SecurityUtils.getSubject().getSession();
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        ModelAndView model = new ModelAndView("attendance/retroactive");
        /*参照打卡方法*/
        List<AttendanceDO> attendanceDOList = attendanceService.findAttendanceByUserId(userInfo.getId(), 0);
        List<AttendanceDO> attendanceDOS = attendanceService.findAttendanceByUserId(userInfo.getId(), 1);
        model.addObject("zcDate", attendanceDOList);
        model.addObject("qqDate", attendanceDOS);
        Date systemDate = new Date();
        // 格式化后的日期值字符串
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String systemDateStr = simpleDateFormat.format(systemDate);
        model.addObject("YearMonth", systemDateStr);//拿给前台做判断只能补卡当月的数据
        return model;
    }

    /**
     * 补卡记录提交
     *
     * @param dateStr
     * @return
     */
    @RequestMapping("/retroactiveSave")
    @ResponseBody
    public ResultSet retroactiveSave(String dateStr) {
        // 由于可能补卡记录是批量提交多个日期，所以传过来是yyyy-mm-dd，yyyy-mm-dd的字符串，根据规则使用“，”切割然后存入数组
        String[] dateArr = dateStr.split(",", -1);
        /**
         * 获取存在session对象中，当前登录的对象，从中获取用户id
         */
        Session session = SecurityUtils.getSubject().getSession();
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        Date systemDate = new Date();
        // 格式化后的日期值字符串
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String yearMonth = simpleDateFormat.format(systemDate);
        // 查询补卡记录表，然后将日期当做key，查询的实体当做valua存入map
        List<RetroactiveDO> retroactiveDOS = attendanceService.findRetroactiveById(userInfo.getId(), systemDate);
        Map<Date, RetroactiveDO> map = new HashMap<>();
        for (RetroactiveDO retroactiveDO : retroactiveDOS) {
            map.put(retroactiveDO.getTime(), retroactiveDO);
        }
        // 定义一个补卡实体集合（稍后做批量新增使用）
        List<RetroactiveDO> retroactiveDOList = new ArrayList<RetroactiveDO>();
        String idStr = null;
        if (0 != dateArr.length) {
            for (String date : dateArr) {//遍历待补卡的数组
                RetroactiveDO retroactiveDO = new RetroactiveDO();
                retroactiveDO.setTime(dateStrToDate(date));
                retroactiveDO.setUserId(userInfo.getId());
                if (map.size() != 0) {
                    if (!map.containsKey(dateStrToDate(date))) {// 如果查询出当前用户之前提交的补卡记录中不存在本次申请的记录，那么则是新增（避免数据重复）
                        retroactiveDOList.add(retroactiveDO);
                    } else {// 如果存在则表示需要修改，由于通过的不可以在审核了，所以只能是驳回的在此提交的话，所以只需要判断状态是2，将id保存到idStr字符串中用逗号隔开
                        if (2 == map.get(dateStrToDate(date)).getState()) {
                            if (idStr != null) {
                                idStr = idStr + "," + map.get(dateStrToDate(date)).getId();
                            } else {
                                idStr = String.valueOf(map.get(dateStrToDate(date)).getId());
                            }
                        }
                    }
                } else {
                    retroactiveDOList.add(retroactiveDO);//如果map记录为空则一定是新增数据
                }
            }
            // 如果idStr（表示需要修正是驳回状态的记录，重新修改为未审核状态的id，做修改使用）
            if (StringUtils.isNotBlank(idStr)) {
                attendanceService.updateRetroactiveState(idStr, 0);
            }
            if (0 != retroactiveDOList.size()) {// 批量新增记录
                attendanceService.insertRetroactive(retroactiveDOList);
            }
        }
        return success();
    }

    /**
     * 补卡记录列表（参考请假记录）
     *
     * @param pageNum
     * @param pageSize
     * @param YearMonth
     * @param keyword
     * @return
     */
    @RequestMapping("/retroactiveList")
    @ResponseBody
    @Transactional(noRollbackFor = {RuntimeException.class, Exception.class})
    public ModelAndView retroactiveList(@RequestParam(name = "pageNum", required = false, defaultValue = "1") int pageNum,
                                        @RequestParam(name = "pageSize", required = false, defaultValue = "7") int pageSize, String YearMonth, String keyword) {
        ModelAndView model = new ModelAndView("attendance/retroactive_list");
        Session session = SecurityUtils.getSubject().getSession();
        String roleCode = (String) session.getAttribute(Constants.SESSION_USER_ROLE);
        UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
        Date systemDate = new Date();
        if (StringUtils.isNotBlank(YearMonth)) {
            systemDate = dateStrToDate(YearMonth);
        }
        if ("employees".equals(roleCode)) {
            PageInfo<RetroactiveDO> retroactiveDOS = attendanceService.findRetroactiveById(pageNum, pageSize, userInfo.getId(), systemDate);
            model.addObject("retroactiveDOS", retroactiveDOS);
        } else {
            PageInfo<RetroactiveDO> retroactiveDOS = attendanceService.findRetroactive(pageNum, pageSize, systemDate, keyword);
            model.addObject("retroactiveDOS", retroactiveDOS);
        }
        model.addObject("YearMonth", YearMonth);
        model.addObject("keyword", keyword);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String systemYM = simpleDateFormat.format(systemDate);
        model.addObject("systemYM", systemYM);
        return model;
    }

    /**
     * 审批补卡记录
     *
     * @param idStr
     * @param type
     * @return
     */
    @RequestMapping("/updateRetroactiveState")
    @ResponseBody
    public ResultSet updateretroactiveState(String idStr, Integer type) {
        if (StringUtils.isNotBlank(idStr) && null != type) {
            if (1 == type) {// 如果状态是1则表示管理审核通过用户提交的申请（批量审核）
                String MorningId = null;//需要补充上班打卡时间的用户id集合（迟到）
                String AfterId = null;//需要补充下班打卡时间的用户id集合（早退）
                String MorningAndAfterId = null;//需要补充下班和上班打卡时间的用户id集合（迟到跟早退都是的用户）
                String special = null;// 特殊的id集合，就是下班是在次日06：00：:0之前，但是前一天有迟到了的
                // 查询出考勤是异常的，条件是从前台传入的需修改记录的id集合，表关联一下，查询出需要修改的考勤记录
                List<AttendanceDO> attendanceDOList = attendanceService.findAttendanceByRetroactiveId(idStr);
                String sign_in = "09:00:00";//打卡早上时间
                String sign_out = "18:00:00";//打卡下午时间
                String sign_special = "06:00:00";//特殊时间
                String sign_moring = "08:59:00";//修正后的打卡上班时间
                String sign_after = "18:01:00";//修正后打卡下班时间

                // 转换为对应的日期格式
                Date sign_in_date = dateStrToDate(sign_in, "HH:mm:ss");
                Date sign_out_date = dateStrToDate(sign_out, "HH:mm:ss");
                Date sign_special_date = dateStrToDate(sign_special, "HH:mm:ss");
                Date sign_moring_date = dateStrToDate(sign_moring, "HH:mm:ss");
                Date sign_after_date = dateStrToDate(sign_after, "HH:mm:ss");
                //遍历考勤记录
                for (AttendanceDO attendanceDO : attendanceDOList) {
                    int state = attendanceDO.getState();
                    Date morning = attendanceDO.getMorning();
                    Date afternoon = attendanceDO.getAfternoon();
                    if (1 == state) { //1表示迟到/早退
                        // 早上迟到下午没早退
                        if (morning.after(sign_in_date) && afternoon.after(sign_out_date)) {
                            if (null == MorningId) {
                                MorningId = String.valueOf(attendanceDO.getId());
                            } else {
                                MorningId = MorningId + "," + attendanceDO.getId();
                            }
                        }
                        // 早上没迟到下午早退
                        if (morning.before(sign_in_date) && afternoon.before(sign_out_date)) {
                            if (null == AfterId) {
                                AfterId = String.valueOf(attendanceDO.getId());
                            } else {
                                AfterId = AfterId + "," + attendanceDO.getId();
                            }
                        }
                        // 早上迟到下午早退
                        if (morning.after(sign_in_date) && afternoon.before(sign_out_date)) {
                            if (null == MorningAndAfterId) {
                                MorningAndAfterId = String.valueOf(attendanceDO.getId());
                            } else {
                                MorningAndAfterId = MorningAndAfterId + "," + attendanceDO.getId();
                            }
                        }
                    } else if (2 == state) { //2表示只有一次打卡记录
                        // 判断是上班打卡记录不存在，下班打卡记录存在且正常
                        if (null == morning && afternoon.after(sign_out_date)) {
                            if (null == MorningId) {
                                MorningId = String.valueOf(attendanceDO.getId());
                            } else {
                                MorningId = MorningId + "," + attendanceDO.getId();
                            }
                        }
                        // 判断是上班打卡记录不存在，下班打卡记录存在并且是在次日6点前的，那么就特殊处理
                        if (null == morning && afternoon.before(sign_special_date)) {
                            if (null == special) {
                                special = String.valueOf(attendanceDO.getId());
                            } else {
                                special = special + "," + attendanceDO.getId();
                            }
                        }
                        // 判断是下班打卡记录不存在
                        if (null == afternoon) {
                            if (null == AfterId) {
                                AfterId = String.valueOf(attendanceDO.getId());
                            } else {
                                AfterId = AfterId + "," + attendanceDO.getId();
                            }
                        }
                    } else if (4 == state) {// 如果状态是4则表示特殊时间，下班打卡在次日6点前，特殊情况特殊处理
                        if (null == special) {
                            special = String.valueOf(attendanceDO.getId());
                        } else {
                            special = special + "," + attendanceDO.getId();
                        }
                    }
                }
                // 修改需要补充早晨打卡时间的id集合
                if (null != MorningId) {
                    attendanceService.updateAttendance(sign_moring_date, sign_after_date, MorningId, 1);
                }
                // 修改需要补充下午打卡时间的id集合
                if (null != AfterId) {
                    attendanceService.updateAttendance(sign_moring_date, sign_after_date, AfterId, 2);
                }
                // 修改需要补充的特殊打卡，修改状态到3，表示在特殊时间打的卡
                if (null != special) {
                    attendanceService.updateAttendance(sign_moring_date, sign_after_date, special, 3);
                }
                // 修改需要补充上午和下午打卡时间的id集合
                if (null != MorningAndAfterId) {
                    attendanceService.updateAttendance(sign_moring_date, sign_after_date, MorningAndAfterId, 4);
                }
            }
            attendanceService.updateRetroactiveState(idStr, type);
            return success();
        } else {
            return error();
        }
    }

    /**
     * 删除补卡记录（参照请假删除）
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteRetroactive")
    @ResponseBody
    @Transactional(noRollbackFor = {RuntimeException.class, Exception.class})
    public ResultSet deleteretroactive(Long id) {
        try {
            attendanceService.deleteRetroactive(id);
            return success();
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logPrint(AttendanceController.class, id, e.getMessage());
            return error();
        }
    }
}
