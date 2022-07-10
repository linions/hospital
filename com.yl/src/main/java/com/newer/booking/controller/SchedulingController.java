package com.newer.booking.controller;


import com.newer.booking.pojo.V_DoctorToPatient;
import com.newer.booking.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/hospital/dept")
public class SchedulingController {
    //yxy
    @Autowired
    private SchedulingService schedulingService;

    @GetMapping("/{department}")
    public  List<HashMap<String,Object>> findId(@PathVariable("department") String department) {
//    public  List<Scheduling> find(@PathVariable("id") int id) {
        return schedulingService.find(department);
    }

    @GetMapping("/{department}/{time}")
    public  List<HashMap<String,Object>> findDoc(@PathVariable("department") String department,@PathVariable("time") String time){
        return schedulingService.findD(department,time);
    }

    @PutMapping("/{did}/{time}")
    public ResponseEntity<V_DoctorToPatient> updateCount(@PathVariable("did") int did,@PathVariable("time") String time,
                                                         @RequestBody V_DoctorToPatient v_doctorToPatient) {
        v_doctorToPatient.setDid(did);
        v_doctorToPatient.setTime(time);
        return schedulingService.update(v_doctorToPatient);
    }




}
