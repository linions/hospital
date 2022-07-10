package com.newer.booking.controller;


import com.newer.booking.pojo.Department;

import com.newer.booking.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
//@RequestMapping("/department")

public class DepartmentController {
    //lj
    @Autowired
   private DepartmentService departmentService;

    @ResponseBody
    @RequestMapping("/department/find")
    public List<Department> findParent(){
        List<Department> list =departmentService.findParent();
        return list;
    }
//yxy
    @GetMapping("/hospital/dept")
    public List<Department> findAll(){
        return departmentService.findAll();
    }

}
