package com.newer.booking.service;

import com.newer.booking.pojo.V_DoctorToPatient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public interface SchedulingService {
    //yxy
    List<HashMap<String,Object>> find(String department);

    List<HashMap<String,Object>> findD(String department, String time);

    ResponseEntity<V_DoctorToPatient> update(V_DoctorToPatient v_doctorToPatient);

//    int findNum(V_DoctorToPatient v_doctorToPatient);
}
