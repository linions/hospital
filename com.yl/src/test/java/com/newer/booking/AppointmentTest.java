//package com.newer.booking;
//
//
//import com.newer.booking.mapper.AppointmentMapper;
//import com.newer.booking.pojo.Appointment;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Scanner;
//
//@SpringBootTest
//public class AppointmentTest {
//    @Autowired
//    private AppointmentMapper appointmentMapper;
//    int count=1;
//    @Test
//    public void addAppointment(){
//        Appointment appointment=new Appointment();
//        int randomNum = (int)((Math.random()*9+1)*100000);//生成6位随机数
//        appointment.setId(randomNum);//设置预约编号
//        Scanner scanner=new Scanner(System.in);
//        System.out.println("请输入患者ID：");
//        appointment.setPatient(scanner.nextInt());
//        System.out.println("请输入医生ID：");
//        appointment.setDoctor(scanner.nextInt());
//        System.out.println("请输入要预约的门诊日期（YYYY-MM-DD）：");
//        appointment.setTime(scanner.next());
//        //设置预约号
//        appointment.setNum(count);
//        count=count++;
//        System.out.println("请输入就诊科室：");
//        appointment.setClinic(scanner.next());
//    }
//
//}
