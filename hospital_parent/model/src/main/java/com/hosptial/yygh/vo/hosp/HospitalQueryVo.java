package com.hosptial.yygh.vo.hosp;

import com.hosptial.yygh.model.base.BaseEntity;
import com.hosptial.yygh.model.hosp.BookingRule;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import java.io.Serializable;

@Data
@ApiModel(description = "Hospital")
public class HospitalQueryVo extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "医院编号")
	@Indexed(unique = true) //唯一索引
	private String hoscode;

	@ApiModelProperty(value = "医院名称")
	@Indexed //普通索引
	private String hosname;

	@ApiModelProperty(value = "医院类型")
	private Long hostype;

	@ApiModelProperty(value = "省code")
	private Long provinceCode;

	@ApiModelProperty(value = "市code")
	private Long cityCode;

	@ApiModelProperty(value = "区code")
	private Long districtCode;

	@ApiModelProperty(value = "详情地址")
	private String address;

	@ApiModelProperty(value = "医院logo")
	private String logoData;

	@ApiModelProperty(value = "医院简介")
	private String intro;

	@ApiModelProperty(value = "坐车路线")
	private String route;

	@ApiModelProperty(value = "状态 0：未上线 1：已上线")
	private Integer status;

	@ApiModelProperty(value = "预约规则")
	private String bookingRule;

}

