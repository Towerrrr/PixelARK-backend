package com.t0r.pixelarkbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.t0r.pixelarkbackend.exception.BusinessException;
import com.t0r.pixelarkbackend.exception.ErrorCode;
import com.t0r.pixelarkbackend.exception.ThrowUtils;
import com.t0r.pixelarkbackend.model.dto.space.SpaceAnalyzeRequest;
import com.t0r.pixelarkbackend.model.entity.Picture;
import com.t0r.pixelarkbackend.model.entity.Space;
import com.t0r.pixelarkbackend.model.entity.User;
import com.t0r.pixelarkbackend.service.SpaceAnalyzeService;
import com.t0r.pixelarkbackend.service.SpaceService;
import com.t0r.pixelarkbackend.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpaceAnalyzeServiceImpl implements SpaceAnalyzeService {

    @Resource
    private UserService userService;

    @Resource
    private SpaceService spaceService;

    /**
     * 校验空间分析权限
     *
     * @param spaceAnalyzeRequest
     * @param loginUser
     */
    private void checkSpaceAnalyzeAuth(SpaceAnalyzeRequest spaceAnalyzeRequest, User loginUser) {
        // 检查权限
        if (spaceAnalyzeRequest.isQueryAll() || spaceAnalyzeRequest.isQueryPublic()) {
            // 全空间分析或者公共图库权限校验：仅管理员可访问
            ThrowUtils.throwIf(!userService.isAdmin(loginUser), ErrorCode.NO_AUTH_ERROR, "无权访问公共图库");
        } else {
            // 私有空间权限校验
            Long spaceId = spaceAnalyzeRequest.getSpaceId();
            ThrowUtils.throwIf(spaceId == null || spaceId <= 0, ErrorCode.PARAMS_ERROR);
            Space space = spaceService.getById(spaceId);
            ThrowUtils.throwIf(space == null, ErrorCode.NOT_FOUND_ERROR, "空间不存在");
            // todo 添加空间权限校验
            spaceService.checkSpaceAuth(loginUser, space);
        }
    }

    /**
     * 根据分析范围填充查询对象
     *
     * @param spaceAnalyzeRequest
     * @param queryWrapper
     */
    private static void fillAnalyzeQueryWrapper(SpaceAnalyzeRequest spaceAnalyzeRequest, QueryWrapper<Picture> queryWrapper) {
        if (spaceAnalyzeRequest.isQueryAll()) {
            return;
        }
        if (spaceAnalyzeRequest.isQueryPublic()) {
            queryWrapper.isNull("spaceId");
            return;
        }
        Long spaceId = spaceAnalyzeRequest.getSpaceId();
        if (spaceId != null) {
            queryWrapper.eq("spaceId", spaceId);
            return;
        }
        throw new BusinessException(ErrorCode.PARAMS_ERROR, "未指定查询范围");
    }

}
