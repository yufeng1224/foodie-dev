package com.yufeng.controller.center;

import com.yufeng.pojo.Users;
import com.yufeng.pojo.bo.center.CenterUserBO;
import com.yufeng.service.center.CenterUserService;
import com.yufeng.utils.CookieUtils;
import com.yufeng.utils.IMOOCJSONResult;
import com.yufeng.utils.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-12-07
 */

@Api(value = "用户信息接口", tags = {"用户信息相关接口"})
@RestController
@RequestMapping("userInfo")
public class CenterUserController {

    @Autowired
    private CenterUserService centerUserService;


    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "GET")
    @PostMapping("update")
    public IMOOCJSONResult update(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @RequestParam String userId, @RequestBody @Valid CenterUserBO centerUserBO,
            BindingResult result,
            HttpServletRequest request, HttpServletResponse response) {

        // 判断 BindingResult 是否保存错误的验证信息, 如果有, 则直接 return
        /** 可以使用postman 或者 swagger 来进行验证 */
        if (result.hasErrors()) {
           Map<String, String> errorMap = getErrors(result);
           return IMOOCJSONResult.errorMap(errorMap);               // 错误集
        }


        Users userResult = centerUserService.updateUserInfo(userId, centerUserBO);
        userResult = setNullProperty(userResult);

        // 需要设置为true, 表示加密
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        //TODO 后续要改, 增加令牌token， 会整合进redis, 分布式会话

        return IMOOCJSONResult.ok();
    }


    private Map<String, String> getErrors(BindingResult result) {

        Map<String, String> map = new HashMap<>();

        List<FieldError> errorList = result.getFieldErrors();
        for (FieldError error : errorList) {
            // 发生验证错误所对应的一个属性
            String errorField = error.getField();

            // 验证错误的信息
            String errorMsg = error.getDefaultMessage();
            map.put(errorField, errorMsg);
        }

        return map;
    }



    /**
     * 数据脱敏: 重要敏感数据不应保存在cookie里
     * @param userResult
     * @return
     */
    private Users setNullProperty(Users userResult) {
        userResult.setPassword(null);
        userResult.setMobile(null);
        userResult.setEmail(null);
        userResult.setCreatedTime(null);
        userResult.setUpdatedTime(null);
        userResult.setBirthday(null);
        return userResult;
    }

}
