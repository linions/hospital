package com.newer.booking.service;

import com.newer.booking.pojo.Appointment;
import com.newer.booking.pojo.V_DoctorToPatient;
import com.newer.booking.pojo.V_appointment;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

@Service
public interface AppointmentService {
    ResponseEntity<Appointment> addAppointment(Appointment appointment);

    int selectNum(int id, String time);

    ResponseEntity<Appointment> update(Appointment appointment);

    List<V_appointment> findIdCard(String id_card);
//    List<HashMap<String,Object>> findIdCard(String id_card);

    void remove(int id);

}
