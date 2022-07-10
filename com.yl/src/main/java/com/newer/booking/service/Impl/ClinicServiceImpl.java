package com.newer.booking.service.Impl;

import com.newer.booking.mapper.ClinicMapper;
import com.newer.booking.pojo.Clinic;
import com.newer.booking.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClinicServiceImpl implements ClinicService {
    @Autowired
    private ClinicMapper clinicMapper;

    @Override
    public List<Clinic> findAllClinic() {
        List<Clinic> list=clinicMapper.findAll();
        return list;
    }
}
