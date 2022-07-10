package com.newer.booking.pojo;


import lombok.Data;

import java.util.List;

@Data
public class Doctor {
    private int id;
    private String name;
    //department类型不同
//    private int  department;
    //add
    private List<Department> parent;
    private Department department;
    private List<Department> sub;

    private String level;
}
