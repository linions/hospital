package com.newer.booking.service;

import com.newer.booking.pojo.Clinic;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ClinicService {
    //查询所有科室
    List<Clinic> findAllClinic();

}
