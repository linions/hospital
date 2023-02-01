package com.hospital.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hosptial.yygh.model.user.UserInfo;
import com.hosptial.yygh.vo.user.UserInfoQueryVo;

public interface UserInfoService extends IService<UserInfo> {
    IPage<UserInfo> selectPage(Long page, Long limit, UserInfoQueryVo userInfoQueryVo);
}
