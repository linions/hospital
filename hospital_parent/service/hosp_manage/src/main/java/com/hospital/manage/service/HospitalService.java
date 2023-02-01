package com.hospital.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hospital.yygh.common.result.Result;
import com.hosptial.yygh.model.hosp.Hospital;
import com.hosptial.yygh.vo.hosp.HospitalQueryVo;


public interface HospitalService extends IService<Hospital> {
    
    void updateStatus(long id, int status);

    boolean updateHosp(HospitalQueryVo hospitalVo);

    boolean saveHosp(Hospital hospital);

    Result selectHospPage(int page, int pageSize, Hospital hospital);

    Result getHospByCode(String hoscode);

    Result findByName(String hosname);
}
