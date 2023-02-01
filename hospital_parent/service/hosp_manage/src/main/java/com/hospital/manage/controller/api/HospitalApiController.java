package com.hospital.manage.controller.api;

import com.hospital.manage.service.HospitalService;
import com.hospital.yygh.common.result.Result;
import com.hosptial.yygh.model.hosp.Hospital;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hosp/hospital")
public class HospitalApiController {
    @Autowired
    private HospitalService hospitalService;

    @ApiOperation("查询医院列表")
    @GetMapping("/findHospList/{page}/{pageSize}")
    public Result findHospList(@PathVariable int page,
                                       @PathVariable int pageSize,
                                       @RequestBody Hospital hospital){
        return hospitalService.selectHospPage(page, pageSize, hospital);
    }

    @ApiOperation("根据医院名称查询医院")
    @GetMapping("/findHospByName/{hosname}")
    public Result findByHosName(@PathVariable String hosname){
        return hospitalService.findByName(hosname);
    }

}
