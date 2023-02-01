package com.hospital.manage.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hosptial.yygh.model.hosp.Doctor;
import com.hosptial.yygh.model.hosp.Hospital;
import com.hosptial.yygh.vo.hosp.HospitalQueryVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DoctorMapper extends BaseMapper<Doctor> {

}
