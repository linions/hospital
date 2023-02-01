package com.hospital.cmn.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hospital.cmn.mapper.DictMapper;
import com.hosptial.yygh.model.cmn.Dict;
import com.hosptial.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EasyExcelListener extends AnalysisEventListener<DictEeVo> {

    private DictMapper dictMapper;

    public EasyExcelListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }

    //    一行一行读取
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict  dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}