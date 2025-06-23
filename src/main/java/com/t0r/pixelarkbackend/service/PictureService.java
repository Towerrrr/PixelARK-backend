package com.t0r.pixelarkbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.t0r.pixelarkbackend.model.dto.picture.PictureQueryRequest;
import com.t0r.pixelarkbackend.model.dto.picture.PictureReviewRequest;
import com.t0r.pixelarkbackend.model.dto.picture.PictureUploadByBatchRequest;
import com.t0r.pixelarkbackend.model.dto.picture.PictureUploadRequest;
import com.t0r.pixelarkbackend.model.entity.Picture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.t0r.pixelarkbackend.model.entity.User;
import com.t0r.pixelarkbackend.model.vo.PictureVO;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
* @author Towerrrr
* @description 针对表【picture(图片)】的数据库操作Service
* @createDate 2025-05-09 16:09:31
*/
public interface PictureService extends IService<Picture> {

    /**
     * 上传图片
     *
     * @param inputSource
     * @param pictureUploadRequest
     * @param loginUser
     * @return
     */
    PictureVO uploadPicture(Object inputSource,
                            PictureUploadRequest pictureUploadRequest,
                            User loginUser);

    /**
     * 获取查询条件
     *
     * @param pictureQueryRequest
     * @return
     */
    public QueryWrapper<Picture> getQueryWrapper(PictureQueryRequest pictureQueryRequest);

    /**
     * 获取图片封装
     * @param picture
     * @param request
     * @return
     */
    public PictureVO getPictureVO(Picture picture, HttpServletRequest request);

    /**
     * 分页获取图片封装
     * @param picturePage
     * @param request
     * @return
     */
    public Page<PictureVO> getPictureVOPage(Page<Picture> picturePage, HttpServletRequest request);

    /**
     * 校验图片信息
     *
     * @param picture
     */
    public void validPicture(Picture picture);

    /**
     * 图片审核
     *
     * @param pictureReviewRequest
     * @param loginUser
     */
    void doPictureReview(PictureReviewRequest pictureReviewRequest, User loginUser);

    /**
     * 填充审核参数
     *
     * @param picture
     * @param loginUser
     */
    public void fillReviewParams(Picture picture, User loginUser);

    /**
     * 批量抓取和创建图片
     *
     * @param pictureUploadByBatchRequest
     * @param loginUser
     * @return 成功创建的图片数
     */
    Integer uploadPictureByBatch(
            PictureUploadByBatchRequest pictureUploadByBatchRequest,
            User loginUser
    );

    /**
     * 异步清理图片文件
     *
     * @param oldPicture
     */
    public void clearPictureFile(Picture oldPicture);
}
