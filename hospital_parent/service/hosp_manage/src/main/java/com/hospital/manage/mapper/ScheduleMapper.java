package com.hospital.manage.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hosptial.yygh.model.hosp.Doctor;
import com.hosptial.yygh.model.hosp.Schedule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ScheduleMapper extends BaseMapper<Schedule> {

    @Update("update schedule set status = #{status} where id = #{id}")
    void updateStatus(@Param("id")long id, @Param("status") int status);
}
