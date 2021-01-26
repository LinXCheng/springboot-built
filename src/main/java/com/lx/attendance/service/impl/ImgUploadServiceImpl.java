package com.lx.attendance.service.impl;

import com.lx.attendance.model.domain.UserDO;
import com.lx.attendance.service.ImgUploadService;
import com.lx.attendance.utils.MessageProperties;
import com.lx.attendance.utils.constants.Constants;
import com.lx.attendance.utils.enums.ImgErrorEnum;
import net.coobird.thumbnailator.Thumbnails;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 图片上传
 */
@Service
public class ImgUploadServiceImpl implements ImgUploadService {

    //用来获取file-message.properties配置文件中的信息
    @Autowired
    private MessageProperties config;

    /**
     * 上传图片
     * @param file
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> uploadPicture(MultipartFile file) {
        Map<String, Object> resMap = new HashMap<String, Object>();
        try {
            String[] IMAGE_TYPE = config.getImageType().split(",");
            String path = null;
            boolean flag = false;
            for (String type : IMAGE_TYPE) {
                if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(), type)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                resMap.put("code", ImgErrorEnum.SUCCESS.getCode());
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                // 获得文件类型
                String fileType = file.getContentType();
                // 获得文件后缀名称
                String imageName = fileType.substring(fileType.indexOf("/") + 1);
                // 原名称
                String oldFileName = file.getOriginalFilename();
                // 新名称
                String newFileName = uuid + "." + imageName;
                // 年月日文件夹
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String basedir = sdf.format(new Date());
                //Session中存在的用戶信息
                Subject subject = SecurityUtils.getSubject();
                Session session = subject.getSession();
    			UserDO userInfo = (UserDO) session.getAttribute(Constants.SESSION_USER_INFO);
                // 进行压缩(大于4M)
                if (file.getSize() > config.getFileSize()) {
                    // 重新生成
                    String newUUID = UUID.randomUUID().toString().replaceAll("-", "");
                    newFileName = newUUID + "." + imageName;
					path = config.getUpPath() + "/" + basedir + "/" + userInfo.getUsername() + "/" + newUUID + "."
							+ imageName;
                    // 如果目录不存在则创建目录
                    File oldFile = new File(path);
                    oldFile = new File(oldFile.getAbsolutePath());
                    if (!oldFile.exists()) {
                        oldFile.getParentFile().mkdirs();
                    }
                    file.transferTo(oldFile);
                    // 压缩图片
                    Thumbnails.of(oldFile).scale(config.getScaleRatio()).toFile(path);
                    // 显示路径
                    resMap.put("path", "imgUpload/" + basedir + "/" + userInfo.getUsername() + "/" + newUUID + "." + imageName);
                } else {
                	path = config.getUpPath() + "/" + basedir + "/" + userInfo.getUsername() + "/" + uuid + "." + imageName;
                    // 如果目录不存在则创建目录
                    File uploadFile = new File(path);
                    uploadFile =  new File(uploadFile.getAbsolutePath());
                    if (!uploadFile.exists()) {
                    	uploadFile.getParentFile().mkdirs();
                     }
                    file.transferTo(uploadFile);
                    // 显示路径
                    resMap.put("path", "imgUpload/" + basedir + "/" + userInfo.getUsername() + "/" + uuid + "." + imageName);
                }
                resMap.put("oldFileName", oldFileName);
                resMap.put("newFileName", newFileName);
                resMap.put("fileSize", file.getSize());
            } else {
                resMap.put("code",ImgErrorEnum.IMAGES_FORMAT.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resMap;
    }
}

