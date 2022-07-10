package com.newer.booking.service;

import com.newer.booking.pojo.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DoctorService {
    List<Doctor> findDoctor();

    List<Doctor> findDoctorByDep(int department);
}
