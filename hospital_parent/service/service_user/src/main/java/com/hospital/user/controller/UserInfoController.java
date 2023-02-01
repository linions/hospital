package com.hospital.user.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital.user.service.UserInfoService;
import com.hospital.yygh.common.result.Result;
import com.hosptial.yygh.model.user.UserInfo;
import com.hosptial.yygh.vo.user.UserInfoQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Api(tags = "医院用户管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/hospUser/info")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation("获取用户列表")
    @PostMapping("/getList/{page}/{limit}")
    public Result list(@PathVariable Long page,
                       @PathVariable Long limit,
                       @RequestBody UserInfoQueryVo userInfoQueryVo) {
        IPage<UserInfo> pageModel = userInfoService.selectPage(page,limit,userInfoQueryVo);
        return Result.ok(pageModel);
    }

    @DeleteMapping("/deleteById/{id}")
    public Result deleteUserInfo(@PathVariable long id) {
        boolean remove = userInfoService.removeById(id);
        if (remove) {
            return Result.ok();
        }else{
            return Result.fail("删除失败");
        }
    }

    //    批量删除医院设置
    @DeleteMapping("/batchRemove/{idList}")
    public Result batchRemoveUserInfo(@PathVariable List<Long> idList){
        userInfoService.removeByIds(idList);
        return Result.ok();
    }

}

