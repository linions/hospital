package com.hospital.manage.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hosptial.yygh.model.hosp.Hospital;
import com.hosptial.yygh.vo.hosp.HospitalQueryVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface HospitalMapper extends BaseMapper<Hospital> {
    @Update("update hospital set status = #{status} where id = #{id}")
    void updateStatus(@Param("id")long id,@Param("status") int status);

    @Update("update hospital set hostype = #{hostype}, province_code = #{provinceCode}, city_code = #{cityCode}, district_code = #{districtCode}, " +
            "address = #{address}, intro = #{intro},route = #{route},logo_data = #{logoData} ,booking_rule = #{bookingRule} where hoscode = #{hoscode}")
    boolean updateHosp(HospitalQueryVo hospitalVo);

    @Insert("insert into hospital(id,hoscode,hosname) values(#{id},#{hoscode}, #{hosname})")
    boolean saveHosp(Hospital hospital);
}
