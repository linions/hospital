package com.hospital.hosp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.hosp.mapper.HospitalSetMapper;
import com.hospital.hosp.service.HospitalSetService;
import com.hosptial.yygh.model.hosp.HospitalSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService {
    @Autowired
    private HospitalSetMapper hospitalSetMapper;

}
