package com.newer.booking.pojo;

import lombok.Data;

@Data
public class V_DoctorToPatient {
    private int id;
    private int did;
    private String doctor;
    private String time;
    private String clinic;
    private int fees;
    private int pid;
    private String department;
    private int count;

}
