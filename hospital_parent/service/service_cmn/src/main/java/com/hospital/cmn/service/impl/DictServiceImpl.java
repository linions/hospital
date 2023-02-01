package com.hospital.cmn.service.impl;


import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.cmn.listener.EasyExcelListener;
import com.hospital.cmn.mapper.DictMapper;
import com.hospital.cmn.service.DictService;
import com.hosptial.yygh.model.cmn.Dict;
import com.hosptial.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {
    @Autowired
    private DictMapper dictMapper;

//    根据数据id查询子数据列表
    @Override
    @Cacheable(value = "dict",keyGenerator = "keyGenerator")
    public List<Dict> findChildData(long id) {
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Dict> dicts = baseMapper.selectList(wrapper);
//        向list集合每个dict对象中设置haschildren
        for (Dict dict: dicts) {
            Long dictId = dict.getId();
            boolean hasChild = this.hasChildren(dictId);
            dict.setHasChildren(hasChild);
        }
        return dicts;
    }

//    导出数据字典信息
    @Override
    public void exportDictData(HttpServletResponse response) {
        try {
//            设置下载信息
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("dictData", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

//            查询数据库信息
            List<Dict> dictList = dictMapper.selectList(null);
            List<DictEeVo> dictVoList = new ArrayList<>(dictList.size());
            for(Dict dict : dictList) {
                DictEeVo dictVo = new DictEeVo();
                BeanUtils.copyProperties(dict, dictVo);
                dictVoList.add(dictVo);
            }
//            调用方法进行写操作
            EasyExcel.write(response.getOutputStream(), DictEeVo.class).sheet("dict").doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    导入数据字典信息
    @Override
    @CacheEvict(value = "dict", allEntries=true)
    public void importDictData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictEeVo.class,new EasyExcelListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getName(Long value, String dictCode) {
        LambdaQueryWrapper<Dict> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Dict::getValue,value);
        if (StringUtils.isNotBlank(dictCode)) {
            Dict dict = this.getDictByDictCode(dictCode);
            queryWrapper.eq(Dict::getParentId, dict.getId());
        }
        Dict finalDict = dictMapper.selectOne(queryWrapper);
        return finalDict.getName();

    }

//    根据dictCode查询其子节点
    @Override
    public List<Dict> findByDictCode(String dictCode) {
//        根据dictCode获取对应的id
        Dict dict = this.getDictByDictCode(dictCode);
//        根据id获取其子节点
        List<Dict> childData = this.findChildData(dict.getId());
        return childData;
    }

    private Dict getDictByDictCode(String dictCode){
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getDictCode, dictCode);
        Dict dict = dictMapper.selectOne(wrapper);
        return dict;
    }

    //    根据id判断是否有孩子节点
    private boolean hasChildren(Long id){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        return count>0;
    }
}
