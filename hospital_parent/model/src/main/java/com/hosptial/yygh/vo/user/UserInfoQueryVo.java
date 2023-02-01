package com.hosptial.yygh.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description="会员搜索对象")
public class UserInfoQueryVo {

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "认证状态")
    private Integer authStatus;

    @ApiModelProperty(value = "创建时间")
    private String createTimeBegin;

    @ApiModelProperty(value = "创建时间")
    private String createTimeEnd;

}
