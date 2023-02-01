package com.hospital.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hospital.manage.mapper.DepartmentMapper;
import com.hospital.manage.service.DepartmentService;
import com.hosptial.yygh.model.hosp.Department;
import com.hosptial.yygh.vo.hosp.DepartmentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> findChildData(String depCode) {
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Department::getBigcode, depCode);
        List<Department> departments = departmentMapper.selectList(queryWrapper);
        for (Department department : departments) {
            String code = department.getDepcode();
            boolean hasChild = this.hasChildren(code);
            department.setHasChildren(hasChild);
        }
        return departments;
    }

//    根据depname、depcode搜索科室信息
    @Override
    public List<Department> searchDepInfo(Department department) {
        LambdaQueryWrapper<Department> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(department.getDepname()), Department::getDepname, department.getDepname());
        queryWrapper.eq(StringUtils.isNotBlank(department.getDepcode()), Department::getDepcode, department.getDepcode());
        List<Department> list = departmentMapper.selectList(queryWrapper);
        for (Department hospDep : list) {
            String dictId = hospDep.getDepcode();
            boolean hasChild = this.hasChildren(dictId);
            hospDep.setHasChildren(hasChild);
        }
        return list;
    }

    public List<DepartmentVo> findDepTree(String hoscode) {
        List<DepartmentVo> result = new ArrayList<>();
        LambdaQueryWrapper<Department> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Department::getHoscode,hoscode);
        wrapper.eq(Department::getBigname,"全部类别");
        List<Department> departmentList = departmentMapper.selectList(wrapper);
//        遍历Map集合
        for (Department department : departmentList){

//            封装大科室
            String depCode = department.getDepcode();
            DepartmentVo departmentVo = new DepartmentVo();
            departmentVo.setDepcode(depCode);
            departmentVo.setDepname(department.getDepname());

//          查询大科室下的子科室
            List<Department> childData = this.findChildData(depCode);

//            封装小科室
            List<DepartmentVo> children = new ArrayList<>();
            for (Department childDep : childData){
                DepartmentVo sdepartmentVo = new DepartmentVo();
                sdepartmentVo.setDepcode(childDep.getDepcode());
                sdepartmentVo.setDepname(childDep.getDepname());
                children.add(sdepartmentVo);
            }
            departmentVo.setChildren(children);
            result.add(departmentVo);
        }
        return result;
    }

    private boolean hasChildren(String depcode){
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.eq("bigcode",depcode);
        Integer count = baseMapper.selectCount(wrapper);
        return count>0;
    }
}

