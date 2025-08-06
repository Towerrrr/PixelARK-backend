package com.t0r.pixelarkbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.t0r.pixelarkbackend.model.entity.SpaceUser;
import com.t0r.pixelarkbackend.service.SpaceUserService;
import com.t0r.pixelarkbackend.mapper.SpaceUserMapper;
import org.springframework.stereotype.Service;

/**
* @author Towerrrr
* @description 针对表【space_user(空间用户关联)】的数据库操作Service实现
* @createDate 2025-08-06 21:47:16
*/
@Service
public class SpaceUserServiceImpl extends ServiceImpl<SpaceUserMapper, SpaceUser>
    implements SpaceUserService{

}




