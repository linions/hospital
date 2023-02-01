package com.hosptial.yygh.model.hosp;


import com.baomidou.mybatisplus.annotation.TableField;
import com.hosptial.yygh.model.base.BaseEntity;
import com.hosptial.yygh.model.base.BaseMongoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>
 * Department
 * </p>
 *
 * @author qy
 */
@Data
@ApiModel(description = "Department")
@Document("Department")
public class Department extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "医院编号")
	@Indexed //普通索引
	private String hoscode;

	@ApiModelProperty(value = "医院编号")
	private String hosname;

	@ApiModelProperty(value = "科室编号")
	@Indexed(unique = true) //唯一索引
	private String depcode;

	@ApiModelProperty(value = "科室名称")
	private String depname;

	@ApiModelProperty(value = "大科室编号")
	private String bigcode;

	@ApiModelProperty(value = "大科室名称")
	private String bigname;

	@ApiModelProperty(value = "科室描述")
	private String intro;

	@ApiModelProperty(value = "是否包含子节点")
	@TableField(exist = false)
	private boolean hasChildren;



}

