package com.lx.attendance.config.exception;

import com.alibaba.fastjson.JSONObject;
import com.lx.attendance.utils.enums.ErrorEnum;

import static com.lx.attendance.utils.ControllerResult.error;

/**
 * 自定义错误类，拦截器可以统一拦截此错误
 */
public class CommonJsonException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7510649689391035942L;
	private Object resultJson;

    public CommonJsonException(ErrorEnum errorEnum) {
        this.resultJson = error(errorEnum);
    }

    public CommonJsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }

    public Object getResultJson() {
        return resultJson;
    }
}
