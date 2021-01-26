package com.lx.attendance.utils;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;

public class BeanMapper {

    /**
     * 转换Collection中对象的类型 sourceList旧的实体类集合，destinationClass新的实体类
     *
     * @param sourceList
     * @param destinationClass
     * @return 新的实体类集合
     */
    @SuppressWarnings({"rawtypes"})
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = null;
        try {
            destinationList = Lists.newArrayList();
            for (Object sourceObject : sourceList) {
                T newModel = destinationClass.newInstance();
                BeanUtils.copyProperties(sourceObject, newModel);
                destinationList.add(newModel);
            }
        } catch (Exception e) {
            logControl.logPrint(BeanMapper.class, null, e.getMessage());
        }
        return destinationList;
    }
}
