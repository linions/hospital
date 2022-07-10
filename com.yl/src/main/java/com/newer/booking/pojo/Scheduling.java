package com.newer.booking.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class Scheduling {
    private int id;

    private int doctor;

    private String time;

    /**
     * 门诊类型
     */
    private Clinic clinic;

    /**
     * 已预约的人数
     */
    private int count;
}
