package com.newer.booking.mapper;


import com.newer.booking.pojo.Clinic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClinicMapper {
    @Select("Select id,title,fees from clinic")
    List<Clinic> findAll();

}
