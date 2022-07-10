package com.newer.booking.mapper;


import com.newer.booking.pojo.Department;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    //lj
    @Select("select id,title,about from department where parent is null")
    @Results(
            value = {
                    @Result(column = "id",property = "id"),
                    @Result(column = "id",property = "parent",many = @Many(select = "findSub"))
            }
    )
    List<Department> findParent();


    //yxy

    @Select("select id ids,title,about from department where parent is null")
    //column 数据库字段名 property 实体属性名
    @Results(
            value = {
                    //为id增加
                    @Result(column = "ids", property = "id"),
                    //这个是为了添加一列 取出id列
                    @Result(
                            column = "ids", property = "sub",
                            many = @Many(select = "findSub")
                    )
            }
    )
    List<Department> findAll();

    @Select("select id,title,about from department where parent = #{id}")
    List<Department> findSub(int id);


    @Select("select id,title,about from department where id = #{id}")
    List<Department> findById(int id);


}
