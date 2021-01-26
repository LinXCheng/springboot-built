package com.lx.attendance.dao;

import com.lx.attendance.model.domain.BusinessTripDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface BusinessTripDao {


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
    public List<BusinessTripDO> findBusinessTripByUserId(@Param("userId") Long userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /**
     * 管理员查询用户请假记录
     * @param startTime
     * @param endTime
     * @param keyword
     * @return
     */
    public List<BusinessTripDO> findBusinessTrip(@Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("keyword") String keyword);

    /**
     * 修改请假记录状态
     * @param id
     * @param type
     */
    public void updateBusinessTripState(@Param("id") Long id, @Param("type") int type, @Param("userId") Long userId);

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
    public BusinessTripDO findBusinessTripBySystemDate(@Param("userId") Long userId, @Param("systemDate") Date systemDate);

    /**
     * 查询某个月份出差记录
     * @param userId
     * @return
     */
    public List<BusinessTripDO> findBusinessTripByDateYM(@Param("userId") Long userId, @Param("dateYM") String dateYM);

}
