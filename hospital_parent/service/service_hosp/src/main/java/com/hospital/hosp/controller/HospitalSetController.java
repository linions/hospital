package com.hospital.hosp.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital.common.utils.MD5;
import com.hospital.hosp.client.HospitalFeignClient;
import com.hospital.hosp.service.HospitalSetService;
import com.hospital.yygh.common.result.Result;
import com.hosptial.yygh.model.hosp.Department;
import com.hosptial.yygh.model.hosp.Hospital;
import com.hosptial.yygh.model.hosp.HospitalSet;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Api(tags = "医院设置管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/hosp/hospSet")
public class HospitalSetController {

    @Autowired
    private HospitalSetService hospitalSetService;


    @Autowired
    private HospitalFeignClient hospitalFeignClient;

//    查询所有医院的信息
    @ApiOperation(value = "获取医院设置所有信息")
    @GetMapping("/findAll")
    public Result findAllHospitalSet(){
        return Result.ok(hospitalSetService.list());
    }

//    逻辑删除医院设置
    @ApiOperation(value = "医院设置逻辑删除")
    @DeleteMapping("/logicDelete/{id}")
    public Result removeHspitalSet(@PathVariable Long id){
        boolean flag = hospitalSetService.removeById(id);
        if(flag){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

//    条件查询分页医院设置信息
    @PostMapping("/pageHospSet/{current}/{limit}")
    public Result pageHospSet(@PathVariable int current,
                              @PathVariable int limit,
                              @RequestBody HospitalSet hospitalSet){

        System.out.println(hospitalSet.getHosname()+  " hospname");
        System.out.println(hospitalSet.getHoscode() + " hoscode");

//       创建page对象，传递当前页面，每页的记录
        Page<HospitalSet> page = new Page<>(current,limit);
        LambdaQueryWrapper<HospitalSet> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(hospitalSet.getHosname()),HospitalSet::getHosname,hospitalSet.getHosname());
        queryWrapper.eq(StringUtils.isNotBlank(hospitalSet.getHoscode()),HospitalSet::getHoscode,hospitalSet.getHoscode());
//        返回结果
        return Result.ok(hospitalSetService.page(page, queryWrapper));
    }

//    添加医院设置信息
    @PostMapping("/saveHospSet")
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor= Exception.class)
    public Result saveHospitalSet(@RequestBody HospitalSet hospitalSet){
//        设置使用状态
        hospitalSet.setStatus(1);
//        签名密钥
        Random random = new Random();
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis()+""+random.nextInt(1000)));
        try {
            boolean saveHospSet = hospitalSetService.save(hospitalSet);
            //        添加医院信息
            Hospital hospital = new Hospital();
            hospital.setHoscode(hospitalSet.getHoscode());
            hospital.setHosname(hospitalSet.getHosname());
            boolean saveHospInfo = hospitalFeignClient.addHospInfo(hospital).isOk();
//            添加医院科室总类别
            Department department = new Department();
            department.setHoscode(hospitalSet.getHoscode());
            department.setHosname(hospitalSet.getHosname());
            department.setDepname("全部类别");
            department.setDepcode(String.valueOf(random.nextInt(10000000)));
            department.setBigcode(String.valueOf(random.nextInt(10000000)));
            department.setBigname("科室管理");
            department.setIntro("科室总类别");
            boolean saveHospDep = hospitalFeignClient.saveHospitalDep(department).isOk();
            if(saveHospSet && saveHospInfo && saveHospDep) return Result.ok();
            else return Result.fail();
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    //    根据id获取医院设置
    @GetMapping("/getHospSetById/{id}")
    public Result GetHospitalById(@PathVariable Long id){
        HospitalSet hospitalSet = hospitalSetService.getById(id);
        return Result.ok(hospitalSet);
    }

//    修改医院设置
    @PutMapping("/updateHospSet")
    public Result updateHospSet(@RequestBody HospitalSet hospitalSet){
        boolean update = hospitalSetService.updateById(hospitalSet);
        if(update) return Result.ok();
        else return Result.fail();
    }

//    批量删除医院设置
    @DeleteMapping("/batchRemove/{idList}")
    public Result batchRemoveHospSet(@PathVariable List<Long> idList){
        hospitalSetService.removeByIds(idList);
        return Result.ok();
    }

//    医院设置锁定和解锁
    @PutMapping("/lockHospSet/{id}/{status}")
    public Result lockHospSet(@PathVariable long id,@PathVariable int status){
//        根据id查询医院信息
        HospitalSet hospitalSet = hospitalSetService.getById(id);
//        设置状态
        hospitalSet.setStatus(status);
//        调用方法
        hospitalSetService.updateById(hospitalSet);
        return  Result.ok();
    }

//    发送签名密钥
    @PutMapping("/setKey/{id}")
    public Result setKey(@PathVariable long id){
    //        根据id查询医院信息
        HospitalSet hospitalSet = hospitalSetService.getById(id);
    //        设置状态
        String signKey = hospitalSet.getSignKey();
        String hoscode = hospitalSet.getHoscode();
    //        发送短信

        return  Result.ok();
    }


    //    分页查询
    @PostMapping("/getHospitalSet/{page}/{pageSize}")
    public Result pageHospSet(@PathVariable int page,
                              @PathVariable int pageSize,
                              @RequestBody Hospital hospital){

        Result result = hospitalFeignClient.pageHospSet(page, pageSize, hospital);

//        返回结果
        return result;
    }

}


