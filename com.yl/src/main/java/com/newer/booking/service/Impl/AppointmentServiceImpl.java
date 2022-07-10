package com.newer.booking.service.Impl;

import com.newer.booking.mapper.AppointmentMapper;
import com.newer.booking.pojo.Appointment;
import com.newer.booking.pojo.V_appointment;
import com.newer.booking.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentMapper appointmentMapper;

    @Override
    public ResponseEntity<Appointment> addAppointment(Appointment appointment) {
        appointmentMapper.addAppointment(appointment);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @Override
    public int selectNum(int id, String time) {
        return appointmentMapper.selectNum(id,time);
    }

    @Override
    public ResponseEntity<Appointment> update(Appointment appointment) {
        appointmentMapper.updateNum(appointment);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @Override
    public List<V_appointment> findIdCard(String id_card) {
        List<V_appointment> list = appointmentMapper.findIdCard(id_card);
        return list;    }

    @Override
    public void remove(int id ) {
        appointmentMapper.remove(id);
    }

//    @Override
//    public int selectNum() {
//        return appointmentMapper.selectNum();
//    }
}
