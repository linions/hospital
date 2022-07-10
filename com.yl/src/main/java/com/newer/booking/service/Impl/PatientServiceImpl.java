package com.newer.booking.service.Impl;

import com.newer.booking.mapper.PatientMapper;
import com.newer.booking.pojo.Patient;
import com.newer.booking.pojo.V_DoctorToPatient;
import com.newer.booking.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientMapper patientMapper;
//yxy
    @Override
    public List<Patient> findAll() {
        List<Patient> list = patientMapper.findAll();
        return list;
    }

    @Override
    public ResponseEntity<Patient> addPatient(Patient patient) {
        patientMapper.addPatient(patient);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Patient> updatePatient(Patient patient) {
        patientMapper.updatePatient(patient);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
}
