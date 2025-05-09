package com.t0r.pixelarkbackend.controller;

import com.t0r.pixelarkbackend.annotation.AuthCheck;
import com.t0r.pixelarkbackend.common.BaseResponse;
import com.t0r.pixelarkbackend.common.ResultUtils;
import com.t0r.pixelarkbackend.constant.UserConstant;
import com.t0r.pixelarkbackend.model.dto.picture.PictureUploadRequest;
import com.t0r.pixelarkbackend.model.entity.User;
import com.t0r.pixelarkbackend.model.vo.PictureVO;
import com.t0r.pixelarkbackend.service.PictureService;
import com.t0r.pixelarkbackend.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/picture")
public class PictureController {

    @Resource
    PictureService pictureService;

    @Resource
    UserService userService;

    /**
     * 上传图片（可重新上传）
     */
    @PostMapping("/upload")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<PictureVO> uploadPicture(
            @RequestPart("file") MultipartFile multipartFile,
            PictureUploadRequest pictureUploadRequest,
            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        PictureVO pictureVO = pictureService.uploadPicture(multipartFile, pictureUploadRequest, loginUser);
        return ResultUtils.success(pictureVO);
    }

}
