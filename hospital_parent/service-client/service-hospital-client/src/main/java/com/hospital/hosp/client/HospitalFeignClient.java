package com.hospital.hosp.client;

import com.hospital.yygh.common.result.Result;
import com.hosptial.yygh.model.hosp.Department;
import com.hosptial.yygh.model.hosp.Hospital;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("hosp-manage")
@Repository
public interface HospitalFeignClient {
    // 获取医院列表数据
    @PostMapping("/admin/hospital/hospData/list/{page}/{pageSize}")
    public Result pageHospSet(@PathVariable("page") int page,
                              @PathVariable("pageSize") int pageSize,
                              @RequestBody Hospital hospital);

    @PostMapping("/admin/hospital/hospData/addHospInfo")
    public Result addHospInfo(@RequestBody Hospital hospital);

    @PostMapping("/admin/hospital/hospDep/saveHospDep")
    public Result saveHospitalDep(@RequestBody Department department);

    //    根据hoscode获取科室列表
    @GetMapping("/admin/hospital/hospDep/getDepList/{hoscode}")
    public Result GetHospDepAll(@PathVariable("hoscode") String hoscode);

    //    根据医院编号查询医院信息
    @GetMapping("/admin/hospital/hospData/getHospInfo/{hoscode}")
    public Result getHospitalInfo(@PathVariable("hoscode") String hoscode);

    //    根据医院编号和科室编号查询医院排班分页信息
    @GetMapping("/admin/hospital/hospSche/getScheByHospAndDepcode/{page}/{pageSize}/{hoscode}/{depcode}")
    public Result getScheByHospAndDepcode(@PathVariable("page") long page,
                                          @PathVariable("pageSize") long pageSize,
                                          @PathVariable("hoscode") String hoscode,
                                          @PathVariable("depcode") String depcode);

    //根据医院编号，科室编号以及排班日期查询排班信息
    @GetMapping("/admin/hospital/hospSche/getScheDetailByWorkDate/{hoscode}/{depcode}/{workDate}")
    public  Result getScheDetailByWorkDate(@PathVariable("hoscode") String hoscode,
                                           @PathVariable("depcode") String depcode,
                                           @PathVariable("workDate") String workDate);
}
