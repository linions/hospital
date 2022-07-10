package com.newer.booking.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class Appointment {
    private int id;
    private String patient;
    private int doctor;
    private String time;
    private int num;
    private String clinic;
    private  int fees;
    private Date generate;
    private String state;
}
