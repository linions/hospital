package com.newer.booking.service.Impl;

import com.newer.booking.mapper.DepartmentMapper;
import com.newer.booking.pojo.Department;
import com.newer.booking.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
//lj
    @Override
    public List<Department> findParent() {
        List<Department> list=departmentMapper.findParent();
        return list;
    }
//yxy
    @Override
    public List<Department> findAll() {
        List<Department> list = departmentMapper.findAll();
        return list;
    }
}
