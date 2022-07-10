package com.newer.booking.controller;


import com.newer.booking.pojo.Clinic;
import com.newer.booking.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@CrossOrigin
@Controller
@RequestMapping("/User")
public class ClinicController {
    @Autowired
    private ClinicService clinicService;

    @ResponseBody
    @RequestMapping("/Clinic")
    public List<Clinic> findClinic(){
        List<Clinic> list= clinicService.findAllClinic();
        return list;
    }
}
