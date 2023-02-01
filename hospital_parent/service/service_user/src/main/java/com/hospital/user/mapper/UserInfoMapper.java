package com.hospital.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hosptial.yygh.model.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
