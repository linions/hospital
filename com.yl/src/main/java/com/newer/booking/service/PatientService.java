package com.newer.booking.service;

import com.newer.booking.pojo.Patient;
import com.newer.booking.pojo.V_DoctorToPatient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PatientService {
    //yxy
    List<Patient> findAll();

    //    @Insert("insert into patient(name,id_card,phone,state) values(#{name},#{id_card},#{phone},#{state})")
    ResponseEntity<Patient> addPatient(Patient patient);

    //lh
    ResponseEntity<Patient> updatePatient(Patient patient);

//    ResponseEntity<Patient> addPatient(Patient patient);



}
