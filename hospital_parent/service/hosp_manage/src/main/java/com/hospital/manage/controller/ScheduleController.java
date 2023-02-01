package com.hospital.manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.hosptial.yygh.vo.hosp.BookingScheduleRuleVo;
import com.hosptial.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Api(tags = "医院排班管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/hospital/hospSche")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private HospitalService hospitalService;

    //根据医院编号，科室编号以及排班日期查询排班信息
    @GetMapping("/getScheDetailByWorkDate/{hoscode}/{depcode}/{workDate}")
    public  Result getScheByHospAndDepcode(@PathVariable String hoscode,
                                           @PathVariable String depcode,
                                           @PathVariable String workDate){
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(workDate);
            LambdaQueryWrapper<Schedule> wrapper= new LambdaQueryWrapper<>();
            wrapper.eq(Schedule::getHoscode,hoscode)
                    .eq(Schedule::getDepcode,depcode)
                    .eq(Schedule::getWorkDate,date);
            List<Schedule> list = scheduleService.list(wrapper);
            for (Schedule schedule : list){
                Doctor doctor = doctorService.getById(schedule.getDocid());
                schedule.getParam().put("doctor",doctor);
                LambdaQueryWrapper<Hospital> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Hospital::getHoscode,hoscode);
                Hospital hospital = hospitalService.getOne(queryWrapper);
                schedule.getParam().put("hosname",hospital.getHosname());
//                Department department = new Department();
                schedule.getParam().put("dayOfWeek",this.getDayOfWeek(new DateTime(schedule.getWorkDate())));
            }
            return Result.ok(list);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }



    //    根据医院和科室获取排班信息的分页查询
    @GetMapping("/getScheByHospAndDepcode/{page}/{pageSize}/{hoscode}/{depcode}")
    public  Result getScheByHospAndDepcode(@PathVariable long page,
                                           @PathVariable long pageSize,
                                           @PathVariable String hoscode,
                                           @PathVariable String depcode){
        List<BookingScheduleRuleVo> bookingScheduleRuleVoList = new ArrayList<>();
        QueryWrapper<Schedule> wrapper = new QueryWrapper<>();
        wrapper.select("work_date as workDate,sum(reserved_number) as reservedNumber,sum(available_number) as availableNumber,count(*) as docCount")
                .eq("hoscode",hoscode)
                .eq("depcode",depcode)
                .groupBy("work_date")
                .orderByAsc("work_date")
                .last("limit " + (page-1) * pageSize + "," + pageSize);
        List<Map<String, Object>> maps = scheduleService.listMaps(wrapper);
        for (Map<String,Object> map :maps){
            BookingScheduleRuleVo bookingScheduleRuleVo = new BookingScheduleRuleVo();
            bookingScheduleRuleVo.setWorkDate((Date) map.get("workDate"));
            String dayOfWeek = this.getDayOfWeek(new DateTime(bookingScheduleRuleVo.getWorkDate()));
            bookingScheduleRuleVo.setDayOfWeek(dayOfWeek);
            bookingScheduleRuleVo.setReservedNumber(Integer.parseInt(map.get("reservedNumber").toString()));
            bookingScheduleRuleVo.setAvailableNumber(Integer.parseInt(map.get("availableNumber").toString()));
            bookingScheduleRuleVo.setDocCount(Integer.parseInt(map.get("docCount").toString()));
            bookingScheduleRuleVo.setStatus(0);
            if (map.get("availableNumber").equals(0)){
                bookingScheduleRuleVo.setStatus(-1);
            }
            bookingScheduleRuleVoList.add(bookingScheduleRuleVo);
        }

        //设置最终数据，进行返回
        Map<String, Object> result = new HashMap<>();
        result.put("bookingScheduleRuleList",bookingScheduleRuleVoList);
        result.put("total",maps.size());

        //获取医院名称
        LambdaQueryWrapper<Hospital> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Hospital::getHoscode,hoscode);
        String hosName = hospitalService.getOne(queryWrapper).getHosname();
        //其他基础数据
        Map<String, String> baseMap = new HashMap<>();
        baseMap.put("hosname",hosName);
        result.put("baseMap",baseMap);

        return Result.ok(result);
    }

//    根据排班id获取排班信息
    @GetMapping("/getScheById/{id}")
    public  Result getScheById(@PathVariable long id){
//        设置查询条件
        LambdaQueryWrapper<Schedule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Schedule::getId,id);
        Schedule schedule = scheduleService.getById(id);
        return Result.ok(schedule);
    }


    //    修改医生排班信息
    @PutMapping("/updateScheInfo")
    public Result updateScheInfo(@RequestBody Schedule schedule){
        boolean update = scheduleService.updateById(schedule);
        if(update) return Result.ok();
        else return Result.fail();
//
    }

    //    添加医生排班信息
    @PostMapping("/addScheInfo")
    public Result addScheInfo(@RequestBody Schedule schedule){
        System.out.println(schedule.toString());
        boolean save = scheduleService.save(schedule);
        if(save) return Result.ok();
        else return Result.fail();
//
    }

//    分页查询
    @PostMapping("/list/{page}/{pageSize}")
    public Result pageHospSet(@PathVariable int page,
                              @PathVariable int pageSize,
                              @RequestBody Schedule schedule){


//       创建page对象，传递当前页面，每页的记录
        Page<Schedule> pageSet = new Page<>(page,pageSize);
        LambdaQueryWrapper<Schedule> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(schedule.getHoscode()!=null,Schedule::getHoscode,schedule.getHoscode());
        queryWrapper.eq(schedule.getDepcode()!=null,Schedule::getDepcode,schedule.getDepcode());
        queryWrapper.eq(schedule.getDocid()!=null,Schedule::getDocid,schedule.getDocid());
        queryWrapper.eq(schedule.getWorkDate()!=null,Schedule::getWorkDate,schedule.getWorkDate());
        queryWrapper.eq(schedule.getStatus()!=null,Schedule::getStatus,schedule.getStatus());

        Page<Schedule> pages = scheduleService.page(pageSet, queryWrapper);
        for (Schedule record : pages.getRecords()) {
            String depcode = record.getDepcode();
            LambdaQueryWrapper<Department> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Department::getDepcode,depcode);
            Department department = departmentService.getOne(wrapper);
            Doctor doctor = doctorService.getById(record.getDocid());
            record.getParam().put("department",department);
            record.getParam().put("doctor",doctor);
        }


//        返回结果
        return Result.ok(pages);
    }


    //    批量删除医院设置
    @DeleteMapping("/batchRemove/{idList}")
    public Result batchRemoveSchedule(@PathVariable List<Long> idList){
        scheduleService.removeByIds(idList);
        return Result.ok();
    }

    //  删除医院排班信息
    @DeleteMapping("/deleteHosSche/{id}")
    public Result deleteHosSchedule(@PathVariable Long id) {
        boolean remove = scheduleService.removeById(id);
        if (remove) {
            return Result.ok();
        }else{
            return Result.fail("删除失败");
        }
    }
    /**
     * 根据日期获取周几数据
     * @param dateTime
     * @return
     */
    private String getDayOfWeek(DateTime dateTime) {
        String dayOfWeek = "";
        switch (dateTime.getDayOfWeek()) {
            case DateTimeConstants.SUNDAY:
                dayOfWeek = "周日";
                break;
            case DateTimeConstants.MONDAY:
                dayOfWeek = "周一";
                break;
            case DateTimeConstants.TUESDAY:
                dayOfWeek = "周二";
                break;
            case DateTimeConstants.WEDNESDAY:
                dayOfWeek = "周三";
                break;
            case DateTimeConstants.THURSDAY:
                dayOfWeek = "周四";
                break;
            case DateTimeConstants.FRIDAY:
                dayOfWeek = "周五";
                break;
            case DateTimeConstants.SATURDAY:
                dayOfWeek = "周六";
            default:
                break;
        }
        return dayOfWeek;
    }





}
