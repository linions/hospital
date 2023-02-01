package com.hospital.cmn.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hospital.cmn.service.DictService;
import com.hospital.yygh.common.result.Result;
import com.hosptial.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "数据字典管理")
@RestController
@CrossOrigin
@RequestMapping("/admin/cmn/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("/findChildData/{id}")
    public Result findChildData(@PathVariable long id){
        List<Dict> list = dictService.findChildData(id);
        return Result.ok(list);
    }

    @ApiOperation(value = "导出数据字典接口")
    @GetMapping("/exportData")
    public Result exportDict(HttpServletResponse response){
        dictService.exportDictData(response);
        return Result.ok();
    }

    @ApiOperation(value = "导入数据字典接口")
    @PostMapping("/importData")
    public Result importDict(MultipartFile file){
        dictService.importDictData(file);
        return Result.ok();
    }

//    根据code和value查询名称
    @GetMapping("/getName/{value}/{dictCode}")
    public String getDictName(@PathVariable Long value,@PathVariable String dictCode){
        String dictName = dictService.getName(value,dictCode);
        return dictName;
    }

    @GetMapping("/getName/{value}")
    public String getDictName(@PathVariable Long value){
        String dictName = dictService.getName(value,"");
        return dictName;
    }

    //根据dictCode获取其下级节点
    @GetMapping("/getByDictCode/{dictCode}")
    public Result<List<Dict>> findByDictCode(@PathVariable String dictCode){
        List<Dict> list = dictService.findByDictCode(dictCode);
        return Result.ok(list);
    }

}


