package com.lx.attendance.utils.enums;

/**
 * 图片上传异常信息枚举类
 */
public enum ImgErrorEnum {
	// 异常信息
	SUCCESS("200", "上传成功"), 
	ERROR("201", "网络异常，请稍后重试"),
	FILE_UPLOAD_ERROR("1002", "图片上传异常"), FILE_UPLOAD_NULL("1003","图片为空"),
	IMAGES_FORMAT("1004","图片格式不正确,支持png|jpg|jpeg");

	/**
	 * 状态码
	 */
	private String code;
	/**
	 * 信息
	 */
	private String message;

	ImgErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

	public String getMessage() {
		return this.message;
	}

	public String getCode() {
		return this.code;
	}

}