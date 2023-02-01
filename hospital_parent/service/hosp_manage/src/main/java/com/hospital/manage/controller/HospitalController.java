package com.hospital.manage.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hospital.cmn.client.DictFeignClient;
import com.hospital.manage.service.HospitalService;
import com.hospital.yygh.common.result.Result;
import com.hosptial.yygh.model.hosp.Hospital;
import com.hosptial.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;

@Api(tags = "医院信息管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/hospital/hospData")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;


    @Value("${hosp.imgPath}")
    private String imgPath;


//    根据医院编号查询医院信息
    @GetMapping("/getHospInfo/{hoscode}")
    public  Result getHospitalInfo(@PathVariable String hoscode){
            Result result = hospitalService.getHospByCode(hoscode);
            return result;
    }

    //    更新医院上线状态
    @PutMapping("/updateStatus/{id}/{status}")
    public Result updateHospStatus(@PathVariable long id,@PathVariable int status){
        //        调用方法
        hospitalService.updateStatus(id,status);
        return  Result.ok();
    }

    //    修改医院信息
    @PutMapping("/updateHospInfo")
    public Result updateHospInfo(@RequestBody Hospital hospital){
//        boolean update = hospitalService.updateHospInfo(hospital);
//        System.out.println(hospital.toString());
        HospitalQueryVo hospitalVo = new HospitalQueryVo();
        BeanUtils.copyProperties(hospital,hospitalVo);
        hospitalVo.setBookingRule(hospital.getBookingRule().toString());
//        System.out.println(hospitalVo.getBookingRule());
        boolean update = hospitalService.updateHosp(hospitalVo);
        if(update) return Result.ok();
        else return Result.fail();
//
    }

    //    添加或者修改医院信息
    @PostMapping("/addHospInfo")
    public Result addHospInfo(@RequestBody Hospital hospital){
        System.out.println(hospital.toString());
        boolean save = hospitalService.saveHosp(hospital);
        if(save) return Result.ok();
        else return Result.fail();
//
    }

//    分页查询
    @PostMapping("/list/{page}/{pageSize}")
    public Result pageHospSet(@PathVariable int page,
                              @PathVariable int pageSize,
                              @RequestBody Hospital hospital){
        Result result = hospitalService.selectHospPage(page,pageSize,hospital);
//        返回结果
        return result;
    }

//        获取查询list集合，遍历进行医院等级封装

//  上传医院logo
    @PostMapping("/uploadLogo")
    public Result uploadHospLogo(@RequestParam("logo") MultipartFile uploadFile ) throws IOException {

        //空文件夹在编译时不会打包进入target中
        File uploadDir = new File(imgPath);
        if (!uploadDir.exists()) {
            return Result.fail("图片上传失败,文件不存在");
        }
        if ( uploadFile != null) {
            //获得上传文件的文件名
            String oldName = uploadFile.getOriginalFilename();
            System.out.println("[上传的文件名]：" + oldName);
            //我的文件保存在static目录下的avatar/user
            File logo = new File(imgPath, oldName);
            try {
                //保存图片
                uploadFile.transferTo(logo);
                //返回成功结果，附带文件的相对路径
                return Result.ok(imgPath);
            }catch (IOException e) {
                e.printStackTrace();
                return Result.fail("图片上传失败");
            }
        }else {
            System.out.println("上传的文件为空");
            return Result.fail("文件传输错误");
        }

    }

    //  删除医院logo
    @DeleteMapping("/uninstallLogo/{fileName}")
    public Result uninstallHospLogo(@PathVariable String fileName) {
        String filePath = imgPath +"\\"+ fileName;
        //空文件夹在编译时不会打包进入target中
        File uploadDir = new File(filePath);
        if (!uploadDir.exists()) {
            return Result.fail("文件不存在");
        } else{
//            删除文件
            try {
                uploadDir.delete();
                return Result.ok("成功删除医院logo");
            }catch (Exception e) {
                e.printStackTrace();
                return Result.fail("删除医院logo失败");
            }
        }
    }



}
