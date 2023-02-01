package com.hospital.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.user.service.UserInfoService;
import com.hosptial.yygh.enums.AuthStatusEnum;
import com.hosptial.yygh.model.user.UserInfo;
import com.hospital.user.mapper.UserInfoMapper;
import com.hosptial.yygh.vo.user.UserInfoQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public IPage<UserInfo> selectPage(Long page, Long limit, UserInfoQueryVo userInfoQueryVo) {
        Page pageSet = new Page(page,limit);
        //UserInfoQueryVo获取条件值
        String phone = userInfoQueryVo.getPhone(); //用户名称
        Integer status = userInfoQueryVo.getStatus();//用户状态
        Integer authStatus = userInfoQueryVo.getAuthStatus(); //认证状态
        String createTimeBegin = userInfoQueryVo.getCreateTimeBegin(); //开始时间
        String createTimeEnd = userInfoQueryVo.getCreateTimeEnd(); //结束时间
        //对条件值进行非空判断
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(phone)) {
            wrapper.like("phone",phone);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }
        if(!StringUtils.isEmpty(authStatus)) {
            wrapper.eq("auth_status",authStatus);
        }
        if(!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge("create_time",createTimeBegin);
        }
        if(!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.le("create_time",createTimeEnd);
        }
        //调用mapper的方法
        IPage<UserInfo> pages = userInfoMapper.selectPage(pageSet, wrapper);
        //编号变成对应值封装
        pages.getRecords().stream().forEach(item -> {
            this.packageUserInfo(item);
        });
        return pages;
    }

    //编号变成对应值封装
    private UserInfo packageUserInfo(UserInfo userInfo) {
        //处理认证状态编码
        userInfo.getParam().put("authStatusString", AuthStatusEnum.getStatusNameByStatus(userInfo.getAuthStatus()));
        //处理用户状态 0  1
        String statusString = userInfo.getStatus().intValue()==0 ?"锁定" : "正常";
        userInfo.getParam().put("statusString",statusString);
        return userInfo;
    }

}
