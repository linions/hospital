package com.hospital.manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital.cmn.client.DictFeignClient;
import com.hospital.manage.service.DepartmentService;
import com.hospital.manage.service.DoctorService;
import com.hospital.manage.service.HospitalService;
import com.hospital.manage.service.ScheduleService;
import com.hospital.yygh.common.result.Result;
import com.hosptial.yygh.model.hosp.Department;
import com.hosptial.yygh.model.hosp.Doctor;
import com.hosptial.yygh.model.hosp.Hospital;
import com.hosptial.yygh.model.hosp.Schedule;
import com.hosptial.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Api(tags = "医院医生管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/hospital/hospDoc")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private DepartmentService departmentService;

//    根据医生id获取医医生信息
    @GetMapping("/getDocById/{id}")
    public Result getHospDoctor(@PathVariable long id){
        Doctor doctor = doctorService.getById(id);
        return Result.ok(doctor);
    }

//    根据hoscode获取所有医生信息
    @ApiOperation(value = "根据hoscode获取所有医生信息")
    @GetMapping("/getHospDoc/{hoscode}")
    public Result GetHospDocAll(@PathVariable String hoscode){
        LambdaQueryWrapper<Doctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Doctor::getHoscode,hoscode);
        return Result.ok(doctorService.list(queryWrapper));
    }

//    根据科室编号查询医生信息
    @ApiOperation(value = "根据科室编号查询医生信息")
    @GetMapping("/getDocByDepcode/{depcode}")
    public  Result getHospitalInfo(@PathVariable String depcode){
//        设置查询条件
        LambdaQueryWrapper<Doctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Doctor::getDepcode,depcode);
        List<Doctor> list = doctorService.list(queryWrapper);
        return Result.ok(list);
    }

    //    修改医生信息
    @PutMapping("/updateDoc")
    @Transactional
    public Result updateDocInfo(@RequestBody Doctor doctor){
        String depcode = doctor.getDepcode();
        Doctor doc = doctorService.getById(doctor);
        if (!doc.getDepcode().equals(depcode)){
            LambdaQueryWrapper<Schedule> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Schedule::getDepcode,doc.getDepcode());
            wrapper.eq(Schedule::getDocid,doctor.getId());
            List<Schedule> list = scheduleService.list(wrapper);
            for (Schedule schedule:list){
                schedule.setDepcode(doctor.getDepcode());
                scheduleService.updateById(schedule);
            }
        }
        boolean update = doctorService.updateById(doctor);


        if(update) return Result.ok();
        else return Result.fail();
//
    }
//
    //    添加医生信息
    @PostMapping("/saveDoc")
    public Result addDocInfo(@RequestBody Doctor doctor){

        doctor.setId((long) (Math.random() * 10000000));
        boolean save = doctorService.save(doctor);
        if(save) return Result.ok();
        else return Result.fail();
//
    }
//
//    分页查询
    @PostMapping("/list/{page}/{pageSize}")
    public Result pageHospSet(@PathVariable int page,
                              @PathVariable int pageSize,
                              @RequestBody Doctor doctor){

//       创建page对象，传递当前页面，每页的记录
        Page<Doctor> pageSet = new Page<>(page,pageSize);
        LambdaQueryWrapper<Doctor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(doctor.getHoscode()!=null,Doctor::getHoscode,doctor.getHoscode());
        queryWrapper.eq(doctor.getId()!=null,Doctor::getId,doctor.getId());
        queryWrapper.eq(doctor.getDepcode()!=null,Doctor::getDepcode,doctor.getDepcode());

        Page<Doctor> pages = doctorService.page(pageSet, queryWrapper);
        for (Doctor record : pages.getRecords()) {
            String depcode = record.getDepcode();
            LambdaQueryWrapper<Department> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Department::getDepcode,depcode);
            Department department = departmentService.getOne(wrapper);
            record.getParam().put("department",department);
        }

//        返回结果
        return Result.ok(pages);
    }
//
//    批量删除医院设置
    @DeleteMapping("/batchRemove/{idList}")
    public Result batchRemoveDoc(@PathVariable List<Long> idList){
        doctorService.removeByIds(idList);
        return Result.ok();
    }

    //  删除医院排班信息
    @DeleteMapping("/deleteDoc/{id}")
    public Result deleteDocInfo(@PathVariable Long id) {
        boolean remove = doctorService.removeById(id);
        if (remove) {
            return Result.ok();
        }else{
            return Result.fail("删除失败");
        }
    }



}
