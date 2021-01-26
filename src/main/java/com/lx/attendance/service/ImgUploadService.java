package com.lx.attendance.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 图片上传
 */
public interface ImgUploadService {
    /**
     * 图片上传
     * @param file
     * @return Map<String, Object>
     */
    public Map<String, Object> uploadPicture(MultipartFile file);
}