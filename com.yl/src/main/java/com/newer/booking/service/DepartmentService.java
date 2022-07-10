package com.newer.booking.service;

import com.newer.booking.pojo.Department;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DepartmentService {
    //查询所有门诊
    //lj
    List<Department> findParent();
    //yxy
    List<Department> findAll();
}
