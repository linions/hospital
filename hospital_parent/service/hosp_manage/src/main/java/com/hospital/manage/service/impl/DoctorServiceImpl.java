package com.hospital.manage.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.manage.mapper.DoctorMapper;
import com.hospital.manage.service.DoctorService;
import com.hosptial.yygh.model.hosp.Doctor;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {


}
