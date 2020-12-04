package com.yufeng.controller;

import com.yufeng.pojo.Users;
import com.yufeng.pojo.bo.UserBO;
import com.yufeng.service.UserService;
import com.yufeng.utils.CookieUtils;
import com.yufeng.utils.IMOOCJSONResult;
import com.yufeng.utils.JsonUtils;
import com.yufeng.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述:
 *      用户注册流程控制模块
 * @author yufeng
 * @create 2020-10-19
 */
@Api(value = "注册登录", tags = {"用户注册登录的相关接口"})   // swagger2配置
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")  // swagger2配置
    @GetMapping("/usernameIsExist")
    public IMOOCJSONResult usernameIsExist(@RequestParam String username) {

        // 1. 判断用户名不能为空   (StringUtils 使用 apache下的工具类)
        if (StringUtils.isBlank(username)) {
            return IMOOCJSONResult.errorMap("用户名不能为空");
        }

        // 2. 查找注册的用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }

        // 3. 请求成功, 用户名没有重复
        return IMOOCJSONResult.ok();
    }


    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")      // swagger2配置
    @PostMapping("/regist")
    public IMOOCJSONResult regist(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) {

        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

        // 1. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password) || StringUtils.isBlank(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }

        // 2. 查询用户名是否存在
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return IMOOCJSONResult.errorMsg("用户名已经存在");
        }

        // 3. 密码长度不能少于6位
        if (password.length() < 6) {
            return IMOOCJSONResult.errorMsg("密码长度不能少于6");
        }

        // 4. 判断两次密码是否一致
        if (!password.equals(confirmPassword)) {
            return IMOOCJSONResult.errorMsg("两次密码输入不一致");
        }

        // 5. 实现注册
        Users userResult = userService.createUser(userBO);

        userResult = setNullProperty(userResult);

        // 需要设置为true, 表示加密
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult),
                true);

        return IMOOCJSONResult.ok();
    }


    /**
     * Cookie 相关知识点
     *       1. Cookie实际上是一小段的文本信息。客户端请求服务器, 如果服务器需要记录用户状态,
     *          就会向客户端浏览器颁发一个Cookie
     *       2. 客户端浏览器会把Cookie保存起来。 当浏览器再请求该网站时, 浏览器把请求的网址连同
     *          该Cookie一同提交给服务器。 服务器检查该Cookie， 以此来辨认用户状态
     * @param userBO
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public IMOOCJSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = userBO.getUsername();
        String password = userBO.getPassword();


        // 1. 判断用户名和密码必须不为空
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return IMOOCJSONResult.errorMsg("用户名或密码不能为空");
        }

        // 2. 实现登录
        Users userResult = userService.queryUserForLogin(username, MD5Utils.getMD5Str(password));
        if (userResult == null) {
            return IMOOCJSONResult.errorMsg("用户名或密码不正确");
        }

        userResult = setNullProperty(userResult);

        // 需要设置为true, 表示加密
        CookieUtils.setCookie(request, response, "user", JsonUtils.objectToJson(userResult), true);

        //TODO 生成用户token, 存入redis会话
        //TODO 同步购物车数据

        return IMOOCJSONResult.ok(userResult);
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


    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")  // swagger2配置
    @PostMapping("/logout")
    public IMOOCJSONResult logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {

        // 清楚用户的相关信息的cookie
        CookieUtils.deleteCookie(request, response, "user");

        //TODO 用户退出登录, 需要情况购物车

        //TODO 分布式会话中需要清除用户数据

        return IMOOCJSONResult.ok();
    }
}
