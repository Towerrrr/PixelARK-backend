package com.t0r.pixelarkbackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.t0r.pixelarkbackend.model.dto.spaceuser.SpaceUserAddRequest;
import com.t0r.pixelarkbackend.model.dto.spaceuser.SpaceUserQueryRequest;
import com.t0r.pixelarkbackend.model.entity.SpaceUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.t0r.pixelarkbackend.model.vo.SpaceUserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author Towerrrr
* @description 针对表【space_user(空间用户关联)】的数据库操作Service
* @createDate 2025-08-06 21:47:16
*/
public interface SpaceUserService extends IService<SpaceUser> {

    // todo 基础服务和方法

    long addSpaceUser(SpaceUserAddRequest spaceUserAddRequest);

    void validSpaceUser(SpaceUser spaceUser, boolean add);

    QueryWrapper<SpaceUser> getQueryWrapper(SpaceUserQueryRequest spaceUserQueryRequest);

    SpaceUserVO getSpaceUserVO(SpaceUser spaceUser, HttpServletRequest request);

    List<SpaceUserVO> getSpaceUserVOList(List<SpaceUser> spaceUserList);

}
