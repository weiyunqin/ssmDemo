package com.gky.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author Kenny Kam
 * @Date 2019/12/8 13:55
 */
@Controller
@RequestMapping("/mvc")
public class MainController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello Controller";
    }

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

//    /**
//     * 注册页面
//     *
//     * @return
//     */
//    @RequestMapping(value = "/register.do", method = RequestMethod.GET)
//    public String register() {
//        return "login";
//    }

    /**
     * 后台主页
     *
     * @return
     */
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "home";
    }
}
