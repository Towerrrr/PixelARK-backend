package com.t0r.pixelarkbackend.service;

import com.t0r.pixelarkbackend.model.dto.space.SpaceAddRequest;
import com.t0r.pixelarkbackend.model.entity.Space;
import com.baomidou.mybatisplus.extension.service.IService;
import com.t0r.pixelarkbackend.model.entity.User;

/**
 * @author Towerrrr
 * @description 针对表【space(空间)】的数据库操作Service
 * @createDate 2025-06-27 22:38:00
 */
public interface SpaceService extends IService<Space> {

    // todo 基础服务方法

    /**
     * 创建空间
     *
     * @param spaceAddRequest
     * @param loginUser
     * @return
     */
    long addSpace(SpaceAddRequest spaceAddRequest, User loginUser);

    /**
     * 校验空间数据
     *
     * @param space
     * @param add
     */
    void validSpace(Space space, boolean add);

    /**
     * 根据空间级别，自动填充限额
     *
     * @param space
     */
    void fillSpaceBySpaceLevel(Space space);

    /**
     * 空间权限校验
     *
     * @param loginUser
     * @param space
     */
    void checkSpaceAuth(User loginUser, Space space);
}
