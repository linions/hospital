package com.newer.booking.controller;

import com.newer.booking.pojo.Doctor;
import com.newer.booking.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @ResponseBody
    @RequestMapping("/findAll")
    public List<Doctor> findDoctor(){
        List<Doctor> list =doctorService.findDoctor();
        return list;
    }
    @ResponseBody
    @RequestMapping("/find")
    public List<Doctor> findDoctorByDep(int department){
        List<Doctor> list =doctorService.findDoctorByDep(department);
        return list;
    }

}

