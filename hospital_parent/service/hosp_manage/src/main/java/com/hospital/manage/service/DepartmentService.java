package com.hospital.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hosptial.yygh.model.hosp.Department;
import com.hosptial.yygh.vo.hosp.DepartmentVo;

import java.util.List;


public interface DepartmentService extends IService<Department> {
    List<Department> findChildData(String depCode);

    List<Department> searchDepInfo(Department department);

    List<DepartmentVo> findDepTree(String hoscode);
}
