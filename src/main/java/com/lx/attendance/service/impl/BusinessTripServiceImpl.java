package com.lx.attendance.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lx.attendance.dao.BusinessTripDao;
import com.lx.attendance.model.domain.BusinessTripDO;
import com.lx.attendance.service.BusinessTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 登录
 */
@Service
public class BusinessTripServiceImpl implements BusinessTripService {

    @Autowired
    private BusinessTripDao businessTripDao;

    /**
     * 新增出差记录
     *
     * @param businessTripDO
     */
    @Override
    public void insertBusinessTrip(BusinessTripDO businessTripDO) {
        businessTripDao.insertBusinessTrip(businessTripDO);
    }

    /**
     * 根据id查询记录
     *
     * @param id
     * @return
     */
    @Override
    public BusinessTripDO findBusinessTripById(Long id) {
        return businessTripDao.findBusinessTripById(id);
    }

    /**
     * 修改出差记录
     *
     * @param businessTripDO
     */
    @Override
    public void updateBusinessTripById(BusinessTripDO businessTripDO) {
        businessTripDao.updateBusinessTripById(businessTripDO);
    }

    /**
     * 用户查询出差记录
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public PageInfo<BusinessTripDO> findBusinessTripByUserId(int pageNum, int pageSize, Long userId, Date startTime, Date endTime) {
        PageHelper.startPage(pageNum, pageSize);
        List<BusinessTripDO> businessTripDOS = businessTripDao.findBusinessTripByUserId(userId, startTime, endTime);
        PageInfo<BusinessTripDO> result = new PageInfo<BusinessTripDO>(businessTripDOS);
        return result;
    }

    /**
     * 管理员查询用户请假记录
     *
     * @param startTime
     * @param endTime
     * @param keyword
     * @return
     */
    @Override
    public PageInfo<BusinessTripDO> findBusinessTrip(int pageNum, int pageSize, Date startTime, Date endTime, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        List<BusinessTripDO> businessTripDOS = businessTripDao.findBusinessTrip(startTime, endTime, keyword);
        PageInfo<BusinessTripDO> result = new PageInfo<BusinessTripDO>(businessTripDOS);
        return result;
    }

    /**
     * 修改请假记录状态
     *
     * @param id
     * @param type
     */
    @Override
    public void updateBusinessTripState(Long id, int type, Long userId) {
        businessTripDao.updateBusinessTripState(id, type, userId);
    }

    /**
     * 删除请假记录必须是未通过申请的
     *
     * @param id
     */
    @Override
    public void deleteBusinessTrip(Long id) {
        businessTripDao.deleteBusinessTrip(id);
    }

    /**
     * 根据系统时间是否存于出差中
     *
     * @param userId
     * @return
     */
    @Override
    public BusinessTripDO findBusinessTripBySystemDate(Long userId, Date systemDate) {
        return businessTripDao.findBusinessTripBySystemDate(userId, systemDate);
    }

    /**
     * 查询某个月份出差记录
     * @param userId
     * @return
     */
    @Override
    public List<BusinessTripDO> findBusinessTripByDateYM(Long userId, String dateYM) {
        return businessTripDao.findBusinessTripByDateYM(userId, dateYM);
    }
}
