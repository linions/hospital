package com.newer.booking.pojo;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Clinic {
    private int id;
    private String title;
    private BigDecimal fees;

}
