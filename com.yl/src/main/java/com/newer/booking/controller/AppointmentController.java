package com.newer.booking.controller;


import com.newer.booking.pojo.Appointment;
import com.newer.booking.pojo.V_appointment;
import com.newer.booking.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/hospital")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/dept")
    public ResponseEntity<Appointment> addAppointments(@RequestBody Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }

    @GetMapping("/hp/{did}/{time}")
    public int findNum(@PathVariable("did") int did,@PathVariable("time") String time){
        return appointmentService.selectNum(did,time);
    }

    @PutMapping("/dept/{id}")
    public ResponseEntity<Appointment> updateNum(@PathVariable("id") int id,
                                                         @RequestBody Appointment appointment) {
        appointment.setId(id);
        return appointmentService.update(appointment);
    }

    //hr
    @ResponseBody
    @RequestMapping("/select/{id_card}")
    public List<V_appointment> findIdCard(@PathVariable("id_card") String id_card) {
        //return appointmentService.findIdCard(id_card);
        return appointmentService.findIdCard(id_card);
    }
    //根据id删除预约记录
    // DELETE /api/patient/id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> remove(
            @PathVariable int id) {

        appointmentService.remove(id);

        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
