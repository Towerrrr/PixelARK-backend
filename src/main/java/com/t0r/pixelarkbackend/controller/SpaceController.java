package com.t0r.pixelarkbackend.controller;

import com.t0r.pixelarkbackend.annotation.AuthCheck;
import com.t0r.pixelarkbackend.auth.SpaceUserAuthManager;
import com.t0r.pixelarkbackend.common.BaseResponse;
import com.t0r.pixelarkbackend.common.ResultUtils;
import com.t0r.pixelarkbackend.constant.UserConstant;
import com.t0r.pixelarkbackend.exception.BusinessException;
import com.t0r.pixelarkbackend.exception.ErrorCode;
import com.t0r.pixelarkbackend.exception.ThrowUtils;
import com.t0r.pixelarkbackend.model.dto.space.SpaceUpdateRequest;
import com.t0r.pixelarkbackend.model.entity.Space;
import com.t0r.pixelarkbackend.model.entity.SpaceLevel;
import com.t0r.pixelarkbackend.model.entity.User;
import com.t0r.pixelarkbackend.model.enums.SpaceLevelEnum;
import com.t0r.pixelarkbackend.model.vo.SpaceVO;
import com.t0r.pixelarkbackend.service.SpaceService;
import com.t0r.pixelarkbackend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/space")
public class SpaceController {

    @Resource
    SpaceService spaceService;

    @Resource
    UserService userService;

    @Resource
    SpaceUserAuthManager spaceUserAuthManager;

    // todo 其他基础接口

    @GetMapping("/get/vo")
    public BaseResponse<SpaceVO> getSpaceVOById(long id, HttpServletRequest request) {
        ThrowUtils.throwIf(id <= 0, ErrorCode.PARAMS_ERROR);
        // 查询数据库
        Space space = spaceService.getById(id);
        ThrowUtils.throwIf(space == null, ErrorCode.NOT_FOUND_ERROR);
        SpaceVO spaceVO = spaceService.getSpaceVO(space, request);
        User loginUser = userService.getLoginUser(request);
        List<String> permissionList = spaceUserAuthManager.getPermissionList(space, loginUser);
        spaceVO.setPermissionList(permissionList);
        // 获取封装类
        return ResultUtils.success(spaceVO);
    }

    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateSpace(@RequestBody SpaceUpdateRequest spaceUpdateRequest) {
        if (spaceUpdateRequest == null || spaceUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 将实体类和 DTO 进行转换
        Space space = new Space();
        BeanUtils.copyProperties(spaceUpdateRequest, space);
        // 自动填充数据
        spaceService.fillSpaceBySpaceLevel(space);
        // 数据校验
        spaceService.validSpace(space, false);
        // 判断是否存在
        long id = spaceUpdateRequest.getId();
        Space oldSpace = spaceService.getById(id);
        ThrowUtils.throwIf(oldSpace == null, ErrorCode.NOT_FOUND_ERROR);
        // 操作数据库
        boolean result = spaceService.updateById(space);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    @GetMapping("/list/level")
    public BaseResponse<List<SpaceLevel>> listSpaceLevel() {
        List<SpaceLevel> spaceLevelList = Arrays.stream(SpaceLevelEnum.values()) // 获取所有枚举
                .map(spaceLevelEnum -> new SpaceLevel(
                        spaceLevelEnum.getValue(),
                        spaceLevelEnum.getText(),
                        spaceLevelEnum.getMaxCount(),
                        spaceLevelEnum.getMaxSize()))
                .collect(Collectors.toList());
        return ResultUtils.success(spaceLevelList);
    }

}
