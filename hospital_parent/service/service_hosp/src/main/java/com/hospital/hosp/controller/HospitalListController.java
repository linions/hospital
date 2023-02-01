package com.hospital.hosp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hospital.cmn.client.DictFeignClient;
import com.hospital.hosp.client.HospitalFeignClient;
import com.hospital.yygh.common.result.Result;
import com.hosptial.yygh.model.hosp.Hospital;
import com.hosptial.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Api(tags = "医院信息列表")
@RestController
//@CrossOrigin
@RequestMapping("/admin/hosp/hospList")
public class HospitalListController {


    @Autowired
    private HospitalFeignClient hospitalFeignClient;

    //根据医院编号，科室编号以及排班日期查询排班信息
    @GetMapping("/getHospScheDetail/{hoscode}/{depcode}/{workDate}")
    public Result getHospScheDetail(@PathVariable String hoscode,
                                    @PathVariable String depcode,
                                    @PathVariable String workDate){
       return hospitalFeignClient.getScheDetailByWorkDate(hoscode,depcode,workDate);
    }

//        根据医院和科室获取排班信息的分页查询
    @GetMapping("/getScheByHospAndDepcode/{page}/{pageSize}/{hoscode}/{depcode}")
    public  Result getPageOfSchedule(@PathVariable long page,
                                           @PathVariable long pageSize,
                                           @PathVariable String hoscode,
                                           @PathVariable String depcode){
        return hospitalFeignClient.getScheByHospAndDepcode(page,pageSize,hoscode,depcode);
    }

    //    通过hoscpde获取某个医院所有科室树形结构数据
    @ApiOperation(value = "通过hoscpde获取某个医院所有科室树形结构数据")
    @GetMapping("/getHospitalDep/{hoscode}")
    public Result GetHospDepAll(@PathVariable String hoscode){
        return hospitalFeignClient.GetHospDepAll(hoscode);
    }


//    根据医院编号查询医院信息
    @GetMapping("/getHospitalInfo/{hoscode}")
    public  Result getHospitalInfo(@PathVariable String hoscode){
        return hospitalFeignClient.getHospitalInfo(hoscode);
    }






}
