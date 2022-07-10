package com.newer.booking.service.Impl;

import com.newer.booking.mapper.SchedulingMapper;
import com.newer.booking.pojo.V_DoctorToPatient;
import com.newer.booking.service.SchedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class SchedulingServiceImpl implements SchedulingService {
    @Autowired
    private SchedulingMapper schedulingMapper;
    //yxy
    @Override
    public List<HashMap<String,Object>> find(String department) {
        return schedulingMapper.find(department);
    }

    @Override
    public List<HashMap<String, Object>> findD(String department, String time) {
        return schedulingMapper.findD(department,time);
    }

    @Override
    public ResponseEntity<V_DoctorToPatient> update(V_DoctorToPatient v_doctorToPatient) {
        schedulingMapper.updateC(v_doctorToPatient);
        return new ResponseEntity<>(v_doctorToPatient, HttpStatus.OK);
    }

//    @Override
//    public  findNum(V_DoctorToPatient v_doctorToPatient) {
//        int timeNew = (int) (System.currentTimeMillis()/ 1000);
//        return schedulingMapper.findNum(v_doctorToPatient);
//    }
}
