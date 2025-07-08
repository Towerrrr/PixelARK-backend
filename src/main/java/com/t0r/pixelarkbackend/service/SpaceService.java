package com.t0r.pixelarkbackend.service;

import com.t0r.pixelarkbackend.exception.BusinessException;
import com.t0r.pixelarkbackend.exception.ErrorCode;
import com.t0r.pixelarkbackend.exception.ThrowUtils;
import com.t0r.pixelarkbackend.model.entity.Space;
import com.baomidou.mybatisplus.extension.service.IService;
import com.t0r.pixelarkbackend.model.enums.SpaceLevelEnum;

/**
* @author Towerrrr
* @description 针对表【space(空间)】的数据库操作Service
* @createDate 2025-06-27 22:38:00
*/
public interface SpaceService extends IService<Space> {

    // todo 基础服务方法

    /**
     * 校验空间数据
     *
     * @param space
     * @param add
     */
    public void validSpace(Space space, boolean add);

    /**
     * 根据空间级别，自动填充限额
     *
     * @param space
     */
    public void fillSpaceBySpaceLevel(Space space);

}
