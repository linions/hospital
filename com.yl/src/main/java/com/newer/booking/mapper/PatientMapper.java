package com.newer.booking.mapper;


import com.newer.booking.pojo.Patient;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PatientMapper {
    //yxy
    @Select("select * from patient")
    List<Patient> findAll();

    @Insert("insert into patient(id,name,phone) values(#{id},#{name},#{phone})")
    void addPatient(Patient patient);

    @Update("update patient set name=#{name},phone=#{phone} where id=#{id}")
    void updatePatient(Patient patient);

    @Update("update patient set state='注销' where id=#{id}")
    void remove(String id);

//    @Delete(("delete from patient where id=#{id}"))
//    void remove(String id);

}
