package com.t0r.pixelarkbackend.service;

import com.t0r.pixelarkbackend.model.dto.picture.PictureUploadRequest;
import com.t0r.pixelarkbackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.t0r.pixelarkbackend.model.entity.User;
import com.t0r.pixelarkbackend.model.vo.PictureVO;
import org.springframework.web.multipart.MultipartFile;

/**
* @author Towerrrr
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-05-09 16:09:31
*/
public interface PictureService extends IService<Picture> {

    /**
     * 上传图片
     *
     * @param multipartFile
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(MultipartFile multipartFile,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

}
