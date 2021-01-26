package com.lx.attendance.controller;

import com.lx.attendance.service.ImgUploadService;
import com.lx.attendance.utils.ResultSet;
import com.lx.attendance.utils.enums.ImgErrorEnum;

import com.lx.attendance.utils.logControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static com.lx.attendance.utils.ControllerResult.error;
import static com.lx.attendance.utils.ControllerResult.success;

/**
 * 图片上传
 */
@Controller
@RequestMapping("/upload")
public class ImgUploadController {
	
    @Autowired
    private ImgUploadService fileUpAndDownService;


    /**
     * 上传图片
     * @param file
     * @return ResultBO
     */
    @RequestMapping(value = "/setFileUpload", method = RequestMethod.POST)
    @ResponseBody
	@Transactional(noRollbackFor={RuntimeException.class, Exception.class})
    public ResultSet<?> setFileUpload(@RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Map<String, Object> resultMap = upload(file);
            if (!ImgErrorEnum.SUCCESS.getCode().equals(resultMap.get("code"))) {
                return error("上传失败!");
            }
            return success(resultMap.get("result"),ImgErrorEnum.SUCCESS.getMessage());
        } catch (Exception e) {
        	logControl.logPrint(ImgUploadController.class,null,ImgErrorEnum.FILE_UPLOAD_ERROR.getMessage());
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return error(ImgErrorEnum.FILE_UPLOAD_ERROR.getMessage());
        }
    }

    /**
     * 图片上传
     * @param file
     * @return Map<String, Object>
     */
	private Map<String, Object> upload(MultipartFile file) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		if (!file.isEmpty()) {
			Map<String, Object> picMap = fileUpAndDownService.uploadPicture(file);
			if (ImgErrorEnum.SUCCESS.getCode().equals(picMap.get("code"))) {
				returnMap.put("code", ImgErrorEnum.SUCCESS.getCode());
				returnMap.put("msg", ImgErrorEnum.SUCCESS.getMessage());
				returnMap.put("result", picMap);
				return returnMap;
			} else {
				returnMap.put("code", ImgErrorEnum.ERROR.getCode());
				returnMap.put("msg", ImgErrorEnum.IMAGES_FORMAT.getMessage());
				return returnMap;
			}
		} else {
			returnMap.put("code", ImgErrorEnum.ERROR.getCode());
			returnMap.put("msg", ImgErrorEnum.FILE_UPLOAD_NULL.getMessage());
			return returnMap;
		}

	}
}
