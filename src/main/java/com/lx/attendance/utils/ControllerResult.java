package com.lx.attendance.utils;

import com.lx.attendance.utils.constants.Constants;

/**
 * controller返回结果类
 */
public class ControllerResult<T> {

    private static final String StingType = "java.lang.String";

    /**
     * 返回成功
     *
     * @return ResultSet
     */
    public static <T>ResultSet<T> success() {
        ResultSet<T> commonEntity = new ResultSet<T>();
        commonEntity.setSuccess(true);
        commonEntity.setMsg(Constants.SUCCESS_MSG);
        commonEntity.setResult(null);
        return commonEntity;
    }

    /**
     * 返回成功 obj如果是String 前端用data.msg取值，否则用data.result
     *
     * @param obj
     * @return ResultSet
     */
    public static <T> ResultSet<T> success(T obj) {
        if (StingType.getClass() == obj.getClass()) {
            return success(null, (String) obj);
        } else {
            return success(obj, Constants.SUCCESS_MSG);
        }
    }

    /**
     * 返回成功
     *
     * @param obj
     * @param msg
     * @return ResultSet
     */
    public static <T> ResultSet<T> success(T obj, String msg) {
        ResultSet<T> commonEntity = new ResultSet<T>();
        commonEntity.setSuccess(true);
        commonEntity.setMsg(msg);
        commonEntity.setResult(obj);
        return commonEntity;
    }

    /**
     * 返回错误
     *
     * @return ResultSet
     */
    public static <T>ResultSet<T> error() {
        ResultSet<T> commonEntity = new ResultSet<T>();
        commonEntity.setSuccess(false);
        commonEntity.setMsg(Constants.ERROR_MSG);
        commonEntity.setResult(null);
        return commonEntity;
    }

    /**
     * 返回错误
     *
     * @param obj
     * @return ResultSet
     */
    public static <T> ResultSet<T> error(T obj) {
        if (StingType.getClass() == obj.getClass()) {
            return error(null, (String) obj);
        } else {
            return error(obj, Constants.ERROR_MSG);
        }
    }

    /**
     * 返回错误
     *
     * @param obj
     * @param msg
     * @return ResultSet
     */
    public static <T> ResultSet<T> error(T obj, String msg) {
        ResultSet<T> commonEntity = new ResultSet<T>();
        commonEntity.setSuccess(false);
        commonEntity.setMsg(msg);
        commonEntity.setResult(obj);
        return commonEntity;
    }
}
