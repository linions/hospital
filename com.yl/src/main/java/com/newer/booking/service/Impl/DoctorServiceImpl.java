package com.newer.booking.service.Impl;

import com.newer.booking.mapper.DoctorMapper;
import com.newer.booking.pojo.Doctor;
import com.newer.booking.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorMapper doctorMapper;

    @Override
    public List<Doctor> findDoctor() {
        List<Doctor> list = doctorMapper.findDoctor();
        return list;
    }

    @Override
    public List<Doctor> findDoctorByDep(int department) {
        List<Doctor> list = doctorMapper.findDoctorByDep(department);
        return list;
    }
}
