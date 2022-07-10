package com.newer.booking.pojo;


import lombok.Data;

import java.util.List;

@Data
public class Department {
    private int id;
    private String title;
    //parent类型不同
//    private List<Department> parent;
    private int parent;

    private String about;

    private List<Department> sub;

}
