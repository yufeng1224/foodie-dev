package com.yufeng.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述:
 *      用户业务对象(前端请求模型)
 * @author yufeng
 * @create 2020-10-20
 */
@ApiModel(value = "用户业务对象", description = "从客户端, 由用户传入的数据封装在此实体中")
@Data
public class UserBO {

    @ApiModelProperty(value = "用户名", name = "用户名", example = "imooc", required = true)
    private String username;

    @ApiModelProperty(value = "密码", name = "密码", example = "123456", required = true)
    private String password;

    @ApiModelProperty(value = "确认密码", name = "确认密码", example = "123456")
    private String confirmPassword;

}
