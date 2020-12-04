package com.yufeng.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 描述:
 *
 * @author yufeng
 * @create 2020-10-01
 */

//@Controller
/** @ApiIgnore: 添加该注释当前controller就不会在swagger2网页中显示 */
@Slf4j
@ApiIgnore
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Object hello() {
        log.debug("debug: hello~");
        log.info("info: hello~");
        log.warn("warn: hello~");
        log.error("error: hello~");
        return "Hello World";
    }


    /**
     * session 相关知识点
     * @param request
     * @return
     */
    @GetMapping("/setSession")
    public Object setSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userInfo", "new user");           // 键值对形式
        session.setMaxInactiveInterval(3600);                         // 当设置为0时，表示永不过期
        session.getAttribute("userInfo");
        //session.removeAttribute("userInfo");
        return "ok";
    }
}
