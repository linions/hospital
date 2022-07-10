package com.newer.booking.mapper;


import com.newer.booking.pojo.Appointment;
import com.newer.booking.pojo.Patient;
import com.newer.booking.pojo.V_appointment;
import org.apache.ibatis.annotations.*;
import org.springframework.http.ResponseEntity;

import javax.websocket.server.PathParam;
import java.util.List;

@Mapper
public interface AppointmentMapper {
    //yxy
    @Insert("insert into appointment(id,patient,doctor,time,clinic,fees) values(#{id},#{patient},#{doctor},#{time},#{clinic},#{fees})")
    void addAppointment(Appointment appointment);

    @Select("select count(*) from appointment where doctor = #{did} and time = #{time}")
    int selectNum(@Param("did") int id, @Param("time") String time);

    @Update("update appointment set num = #{num} where id = #{id}")
    void updateNum(Appointment appointment);

    //hr
    //查询预约记录
    @Select("select * from v_appointmentRecord where id_card = #{id_card} and state='预约'")
    List<V_appointment> findIdCard(String id_card);


    @Update("update appointment set state='取消' where id=#{id}")
    void remove(int id);

}
