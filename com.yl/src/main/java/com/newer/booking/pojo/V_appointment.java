package com.newer.booking.pojo;

import lombok.Data;

@Data
public class V_appointment {
    private int id;  //订单号
    private String pname; //病人姓名
    private String id_card; //身份证号
    private int num;  //就诊序号
    private String time; //看诊时间
    private String clinic; //科室
    private String dname; //医生姓名
    private String generate; //挂号时间
    private int fees; //挂号费用

}
