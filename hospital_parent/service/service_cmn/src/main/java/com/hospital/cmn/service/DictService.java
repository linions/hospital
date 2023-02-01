package com.hospital.cmn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hosptial.yygh.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface DictService extends IService<Dict> {
//    根据数据id查询子数据列表
    List<Dict> findChildData(long id);

    void exportDictData(HttpServletResponse response);

    void importDictData(MultipartFile file);

    String getName(Long value, String dictCode);

    //    根据dictCode查询其子节点
    List<Dict> findByDictCode(String dictCode);
}
