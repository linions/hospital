package com.hospital.manage.controller;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hospital.manage.service.DepartmentService;
import com.hospital.yygh.common.result.Result;
import com.hosptial.yygh.model.hosp.Department;
import com.hosptial.yygh.model.hosp.Hospital;
import com.hosptial.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@Api(tags = "医院科室管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/hospital/hospDep")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;


    //    通过hoscpde获取某个医院所有科室树形结构数据
    @ApiOperation(value = "通过hoscpde获取某个医院所有科室树形结构数据")
    @GetMapping("/getDepList/{hoscode}")
    public Result GetHospDepAll(@PathVariable String hoscode){
        List<DepartmentVo> list = departmentService.findDepTree(hoscode);
        return Result.ok(list);
    }

    //    获取所有子科室
    @ApiOperation(value = "获取所有子科室信息")
    @PostMapping("/getAllChildDep")
    public Result getAllChildDep(@RequestBody Hospital hospital){
        Result result = this.GetHospDepAll(hospital);
        Department department = (Department) result.getData();
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.eq(Department::getHoscode,hospital.getHoscode());
//        System.out.println(department.getDepcode());
        lambdaQueryWrapper.ne(Department::getDepcode,department.getDepcode());
        lambdaQueryWrapper.ne(Department::getBigcode,department.getDepcode());
//        System.out.println(departmentService.list(lambdaQueryWrapper));
        return Result.ok(departmentService.list(lambdaQueryWrapper));
    }

    //   查询某个医院所有的科室信息
    @ApiOperation(value = "获取科室设置所有信息")
    @PostMapping("/getHospDep")
    public Result GetHospDepAll(@RequestBody Hospital hospital){
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Department::getHoscode,hospital.getHoscode());
        queryWrapper.eq(hospital.getHosname()!=null,Department::getHosname,hospital.getHosname());
        queryWrapper.eq(Department::getDepname,"全部类别");
        return Result.ok(departmentService.getOne(queryWrapper));
    }

    //    逻辑删除医院设置
    @ApiOperation(value = "科室设置逻辑删除")
    @DeleteMapping("/logicDelete/{id}")
    public Result removeHospitalDep(@PathVariable Long id){
        Department department = departmentService.getById(id);
        Hospital hospital = new Hospital();
        hospital.setHosname(department.getHosname());
        hospital.setHoscode(department.getHoscode());
        Department depart = (Department) this.GetHospDepAll(hospital).getData();
        if(depart.getDepcode().equals(department.getBigcode())){
            LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Department::getBigcode,department.getDepcode());
            List<Department> list = departmentService.list(queryWrapper);
            for (Department dep:list) {
                departmentService.removeById(dep.getId());
            }
        }
        boolean flag = departmentService.removeById(id);
        if(flag){
            return Result.ok();
        }else {
            return Result.fail();
        }
    }

    //    条件查询分页医院设置信息
    @PostMapping("/searchHospDep")
    public Result searchHospDep(@RequestBody Department department){

        System.out.println("depname=" + department.getDepname());
        System.out.println("depcode=" + department.getDepcode());
        System.out.println(department.toString());

        List<Department> list = departmentService.searchDepInfo(department);


        System.out.println(list);
//        返回结果
        return Result.ok(list);

    }

    //    添加科室设置信息
    @PostMapping("/saveHospDep")
    public Result saveHospitalDep(@RequestBody Department department){

//      设置编码
//        Random random = new Random();
//        department.setDepcode(String.valueOf(random.nextInt(10000000)));
        boolean save = departmentService.save(department);
        if(save) return Result.ok();
        else return Result.fail();

//

    }

    //    根据depcode获取科室设置
    @GetMapping("/getDepByCode/{depcode}")
    public Result GetHospDepById(@PathVariable String depcode){
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Department::getDepcode,depcode);
        Department department = departmentService.getOne(queryWrapper);
        return Result.ok(department);
    }

    //    修改科室设置
    @PutMapping("/updateHospDep")
    public Result updateHospDep(@RequestBody Department department){
        System.out.println("depname=" + department.getDepname());
        System.out.println("depcode=" + department.getDepcode());
        System.out.println(department.toString());
        boolean update = departmentService.updateById(department);
        if(update) return Result.ok();
        else return Result.fail();
    }

    //    批量删除科室设置
    @DeleteMapping("/batchRemove/{idList}")
    public Result batchRemoveHospDep(@PathVariable List<Long> idList){
        for (Long id:idList) {
            Department department = departmentService.getById(id);
            List<Department> childData = (List<Department>) this.findChildData(department.getDepcode()).getData();
            for (Department child:childData) {
                departmentService.removeById(child.getId());
            }
        }
        boolean remove = departmentService.removeByIds(idList);
        if (remove)
            return Result.ok("批量删除成功！");
        return Result.fail();
    }


//    查询出所有的父科室
    @ApiOperation(value = "根据科室编号查询子数据列表")
    @GetMapping("/findChildData/{depCode}")
    public Result findChildData(@PathVariable String depCode){
        List<Department> list = departmentService.findChildData(depCode);
        return Result.ok(list);
    }

}


