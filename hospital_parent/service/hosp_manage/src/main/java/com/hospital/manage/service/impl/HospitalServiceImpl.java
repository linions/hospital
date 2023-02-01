package com.hospital.manage.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.cmn.client.DictFeignClient;
import com.hospital.manage.mapper.HospitalMapper;
import com.hospital.manage.service.HospitalService;
import com.hospital.yygh.common.result.Result;
import com.hosptial.yygh.model.hosp.Hospital;
import com.hosptial.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital> implements HospitalService {
    @Autowired
    private HospitalMapper hospitalMapper;

    @Autowired
    private DictFeignClient dictFeignClient;

    @Override
    public void updateStatus(long id, int status) {
        hospitalMapper.updateStatus(id,status);
    }

    @Override
    public boolean updateHosp(HospitalQueryVo hospitalVo) {
        boolean update = hospitalMapper.updateHosp(hospitalVo);
        return update;
    }

    @Override
    public boolean saveHosp(Hospital hospital) {
        boolean save = hospitalMapper.saveHosp(hospital);
        return save;
    }

    @Override
    public Result selectHospPage(int page, int pageSize, Hospital hospital) {
        //       创建page对象，传递当前页面，每页的记录
        Page<Hospital> pageSet = new Page<>(page,pageSize);
        LambdaQueryWrapper<Hospital> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(hospital.getProvinceCode()!=null,Hospital::getProvinceCode,hospital.getProvinceCode());
        queryWrapper.eq(hospital.getCityCode()!=null,Hospital::getCityCode,hospital.getCityCode());
        queryWrapper.like(StringUtils.isNotBlank(hospital.getHosname()),Hospital::getHosname,hospital.getHosname());

        Page<Hospital> pages = hospitalMapper.selectPage(pageSet, queryWrapper);

//        获取查询list集合，遍历进行医院等级封装
        pages.getRecords().stream().forEach(item ->{
            this.setHospitalType(item);
        });
        return Result.ok(pages);
    }

    @Override
    public Result getHospByCode(String hoscode) {
        //        设置查询条件
        LambdaQueryWrapper<Hospital> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Hospital::getHoscode,hoscode);
        Hospital hospital = hospitalMapper.selectOne(queryWrapper);
        return Result.ok(this.setHospitalType(hospital));
    }

    @Override
    public Result findByName(String hosname) {
        //        设置查询条件
        LambdaQueryWrapper<Hospital> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Hospital::getHosname,hosname);
        List<Hospital> hospitals = hospitalMapper.selectList(queryWrapper);
        for (Hospital hospital:hospitals){
            this.setHospitalType(hospital);
        }
        return Result.ok(hospitals);
    }

    private Hospital setHospitalType(Hospital hospital) {
//        根据dictCode和value获取医院等级名称
        String hostypeName = dictFeignClient.getDictName(hospital.getHostype(), "Hostype");
//        查询省，市，地区
        String provinceName = dictFeignClient.getDictName(hospital.getProvinceCode() );
        String cityName = dictFeignClient.getDictName(hospital.getCityCode());
        String districtName = dictFeignClient.getDictName(hospital.getDistrictCode());
        hospital.getParam().put("hostypeName",hostypeName);
        hospital.getParam().put("provinceName",provinceName);
        hospital.getParam().put("cityName",cityName);
        hospital.getParam().put("districtName",districtName);
        hospital.getParam().put("fullAddress",provinceName+cityName+districtName);
        return hospital;
    }

}
