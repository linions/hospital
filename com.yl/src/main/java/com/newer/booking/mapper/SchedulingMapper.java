package com.newer.booking.mapper;

import com.newer.booking.pojo.V_DoctorToPatient;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface SchedulingMapper {
    //yxy
    @Select("select distinct pid,time,department from v_scheduling where department = #{department} order by time asc")
    List<HashMap<String,Object>> find(String department);

    @Select("select did, doctor, clinic,fees,count from v_scheduling where department = #{department} and time = #{time}")
    List<HashMap<String,Object>> findD(String department, String time);

    @Update("update v_scheduling set count = #{count} where did = #{did} and time = #{time}")
    void updateC(V_DoctorToPatient v_doctorToPatient);

//    @Select("select count(*)+1 from v_scheduling where did = #{did} and time = #{time}")
//    int findNum(V_DoctorToPatient v_doctorToPatient);

//    @Insert("insert into apppoinment() values()")
}
