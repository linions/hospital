//package com.newer.booking;
//
//
//import com.newer.booking.mapper.PatientMapper;
//import com.newer.booking.pojo.Patient;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Scanner;
//
//@SpringBootTest
//public class PatientTest {
//    @Autowired
//    private PatientMapper patientMapper;
//    @Test
//    public void findPatient(){
//        List<Patient> list=patientMapper.findPatient();
//        for(Patient patient:list){
//            System.out.println(patient);
//        }
//    }
//
//
//    @Test
//    public void addPatient(){
//        Patient patient1=new Patient();
//        System.out.println("请输入用户ID：");
//        Scanner scanner=new Scanner(System.in);
//        patient1.setId(scanner.nextInt());
//        System.out.println("请输入用户姓名：");
//        patient1.setName(scanner.next());
//        System.out.println("请输入用户身份证号码：");
//        patient1.setId_card(scanner.next());
//        System.out.println("请输入用户电话号码：");
//        patient1.setPhone(scanner.next());
//        System.out.println("请输入用户状态：");
//        patient1.setState(scanner.next());
//        int count= patientMapper.addPatient(patient1);
//        if (count!=0){
//            System.out.println("添加成功");
//        }else {
//            System.out.println("添加失败");
//        }
//    }
//
//
//    @Test
//    public void updatePatient(){
//        Patient patient=new Patient();
//        System.out.println("请输入要更新的用户ID：");
//        Scanner scanner=new Scanner(System.in);
//        patient.setId(scanner.nextInt());
//        System.out.println("请输入要更新的用户姓名：");
//        patient.setName(scanner.next());
//        System.out.println("请输入要更新的用户身份证号码：");
//        patient.setId_card(scanner.next());
//        System.out.println("请输入要更新的用户电话号码：");
//        patient.setPhone(scanner.next());
//        System.out.println("请输入要更新的用户状态：");
//        patient.setState(scanner.next());
//        int count= patientMapper.updatePatient(patient);
//        if (count!=0){
//            System.out.println("更新成功");
//        }else {
//            System.out.println("更新失败");
//        }
//    }
//
//
//    @Test
//    public void deletePatient(){
//        Patient patient=new Patient();
//        System.out.println("请输入要删除的用户ID：");
//        Scanner scanner=new Scanner(System.in);
//        patient.setId(scanner.nextInt());
//        int count= patientMapper.deletePatient(patient);
//        if (count!=0){
//            System.out.println("删除成功");
//        }else {
//            System.out.println("删除失败");
//        }
//    }
//
//
//}
