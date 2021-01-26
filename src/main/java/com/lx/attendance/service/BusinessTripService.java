package com.lx.attendance.service;


import com.github.pagehelper.PageInfo;
import com.lx.attendance.model.domain.BusinessTripDO;

import java.util.Date;
import java.util.List;

/**
 * 登录
 */
public interface BusinessTripService {

    /**
     * 新增出差记录
     * @param businessTripDO
     */
    public void insertBusinessTrip(BusinessTripDO businessTripDO);

    /**
     * 根据id查询记录
     * @param id
     * @return
     */
    public BusinessTripDO findBusinessTripById(Long id);

    /**
     * 修改出差记录
     * @param businessTripDO
     */
    public void updateBusinessTripById(BusinessTripDO businessTripDO);

    /**
     * 用户查询出差记录
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    public PageInfo<BusinessTripDO> findBusinessTripByUserId(int pageNum, int pageSize, Long userId, Date startTime, Date endTime);

    /**
     * 管理员查询用户请假记录
     * @param startTime
     * @param endTime
     * @param keyword
     * @return
     */
    public PageInfo<BusinessTripDO> findBusinessTrip(int pageNum, int pageSize, Date startTime, Date endTime, String keyword);

    /**
     * 修改请假记录状态
     * @param id
     * @param type
     */
    public void updateBusinessTripState(Long id, int type, Long userId);

    /**
     * 删除请假记录必须是未通过申请的
     * @param id
     */
    public void deleteBusinessTrip(Long id);


    /**
     * 根据系统时间是否存于出差中
     * @param userId
     * @return
     */
    public BusinessTripDO findBusinessTripBySystemDate(Long userId, Date dateYM);

    /**
     * 查询某个月份出差记录
     * @param userId
     * @return
     */
    public List<BusinessTripDO> findBusinessTripByDateYM(Long userId, String dateYM);
}
