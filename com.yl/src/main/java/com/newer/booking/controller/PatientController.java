package com.newer.booking.controller;


import com.newer.booking.mapper.PatientMapper;
import com.newer.booking.pojo.Patient;
import com.newer.booking.pojo.V_DoctorToPatient;
import com.newer.booking.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/hospital/patient")
public class PatientController {
    @Autowired
    private PatientMapper patientMapper;
    //yxy
    @Autowired
    private PatientService patientService;

    //
    @GetMapping("/select")
    public List<Patient> findAll() {
        List<Patient> list = patientService.findAll();
        return list;
    }

    @PostMapping
    public ResponseEntity<Patient> addPatients(@RequestBody Patient patient){
        return patientService.addPatient(patient);
    }

    //根据id修改就诊人信息
    // PUT /api/patient/id
    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(
            @PathVariable String id,
            @RequestBody Patient patient) {
        patient.setId(id);
        return patientService.updatePatient(patient);
    }

    //根据id删除就诊人
    // DELETE /api/patient/delete/id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> remove(
            @PathVariable String id) {

        patientMapper.remove(id);
        //返回对象的JSON列表
        return new ResponseEntity<String>(HttpStatus.OK);
    }


//    @PutMapping("/{did}/{time}")
//    public ResponseEntity<V_DoctorToPatient> updateCount(@PathVariable("did") int did, @PathVariable("time") String time,
//                                                         @RequestBody V_DoctorToPatient v_doctorToPatient) {
//        v_doctorToPatient.setDid(did);
//        v_doctorToPatient.setTime(time);
//        return schedulingService.update(v_doctorToPatient);
//    }
}
