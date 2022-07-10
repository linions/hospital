package com.newer.booking.mapper;

import com.newer.booking.pojo.Department;
import com.newer.booking.pojo.Doctor;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface DoctorMapper {
    //lj
    @Select("select * from doctor")
    List<Doctor> findDoctor();

    @Select("select * from doctor where department=#{department}")
    List<Doctor> findDoctorByDep(int department);
//yxy
    //查询医生信息及所在科室
    @Select("select id,`name`,`department`,`level` from doctor")
    @Results(
            value = {
                    @Result(
                            column = "department",property = "department",
                            many = @Many(select="com.newer.booking.mapper.DepartmentMapper.findById")
                    )
            }
    )

    List<Doctor> findAll();

}
