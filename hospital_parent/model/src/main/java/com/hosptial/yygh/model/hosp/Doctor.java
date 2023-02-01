package com.hosptial.yygh.model.hosp;

import com.hosptial.yygh.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Data
@ApiModel(description = "Doctor")
@Document("Doctor")
public class Doctor extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "医生id")
    private Long id;

    @ApiModelProperty(value = "医院编号")
    @Indexed //普通索引
    private String hoscode;

    @ApiModelProperty(value = "科室编号")
    @Indexed //普通索引
    private String depcode;

    @ApiModelProperty(value = "医生姓名")
    private String name;

    @ApiModelProperty(value = "职称")
    private String title;

    @ApiModelProperty(value = "擅长技能")
    private String skill;

    @ApiModelProperty(value = "挂号费")
	private BigDecimal amount;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "医生介绍")
    private String intro;


}
