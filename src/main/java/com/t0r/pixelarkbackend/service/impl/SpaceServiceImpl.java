package com.t0r.pixelarkbackend.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.t0r.pixelarkbackend.exception.BusinessException;
import com.t0r.pixelarkbackend.exception.ErrorCode;
import com.t0r.pixelarkbackend.exception.ThrowUtils;
import com.t0r.pixelarkbackend.model.entity.Space;
import com.t0r.pixelarkbackend.model.enums.SpaceLevelEnum;
import com.t0r.pixelarkbackend.service.SpaceService;
import com.t0r.pixelarkbackend.mapper.SpaceMapper;
import org.springframework.stereotype.Service;

/**
* @author Towerrrr
* @description 针对表【space(空间)】的数据库操作Service实现
* @createDate 2025-06-27 22:38:00
*/
@Service
public class SpaceServiceImpl extends ServiceImpl<SpaceMapper, Space>
    implements SpaceService{

    // todo 基础服务方法

    /**
     * 校验空间数据
     *
     * @param space
     * @param add
     */
    @Override
    public void validSpace(Space space, boolean add) {
        ThrowUtils.throwIf(space == null, ErrorCode.PARAMS_ERROR);
        // 从对象中取值
        String spaceName = space.getSpaceName();
        Integer spaceLevel = space.getSpaceLevel();
        SpaceLevelEnum spaceLevelEnum = SpaceLevelEnum.getEnumByValue(spaceLevel);
        // 要创建
        if (add) {
            if (StrUtil.isBlank(spaceName)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "空间名称不能为空");
            }
            if (spaceLevel == null) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "空间级别不能为空");
            }
        }
        // 修改数据时，如果要改空间级别
        if (spaceLevel != null && spaceLevelEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "空间级别不存在");
        }
        if (StrUtil.isNotBlank(spaceName) && spaceName.length() > 30) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "空间名称过长");
        }
    }

    // todo 根据空间级别自动填充限额

}




