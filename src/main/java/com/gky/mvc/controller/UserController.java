package com.gky.mvc.controller;

import com.gky.domain.ResponseObj;
import com.gky.domain.User;
import com.gky.service.UserService;
import com.gky.utils.GsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Kenny Kam
 * @Date 2019/12/8 14:00
 */
@Controller
@RequestMapping(value = "/userAction")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public ModelAndView reg(HttpServletRequest req, User user) throws Exception{
        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        if (null == user) {
            mav.addObject("message", "用户信息不能为空");
            return mav;
        }
        if (StringUtils.isBlank(user.getName()) ||
                StringUtils.isBlank(user.getPwd())) {
            mav.addObject("message", "用户名或密码不能为空");
            return mav;
        }
        if (null != userService.findUser(user)) {
            mav.addObject("message", "用户已经存在！");
            return mav;
        }
        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
            mav.addObject("message", "错误：用户其他信息错误");
            return mav;
        }
        mav.addObject("code", 110);
        mav.addObject("message", "恭喜。注册成功");
        req.getSession().setAttribute("user", user);
        return mav;
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Object login(HttpServletRequest req, User user) throws Exception{
        Object result;
        ResponseObj responseObj;
        if (null == user) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("登录信息不能为空");
            result = GsonUtils.gson.toJson(responseObj);
            return result;
        }
        if (StringUtils.isEmpty(user.getLoginId()) || StringUtils.isEmpty(user.getPwd())) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空");
            result = GsonUtils.gson.toJson(responseObj);
            return result;
        }
        //查找用户
        User user1 = userService.findUser(user);
        if (null == user1) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("未找到该用户");
            result = GsonUtils.gson.toJson(responseObj);
        } else {
            if (user.getPwd().equals(user1.getPwd())) {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg(ResponseObj.OK_STR);
                result = GsonUtils.gson.toJson(responseObj);
            } else {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("用户密码错误");
                result = GsonUtils.gson.toJson(responseObj);
            }
        }
        return result;
    }

    @RequestMapping(value = "/uploadHeadPic",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
    public Object uploadHeadPic(@RequestParam(required = false)MultipartFile file,HttpServletRequest req) {
        //在这里面文件存储的方案一般是：收到文件→获取文件名→在本地存储目录建立防重名文件→写入文件→返回成功信息
        //如果上面的步骤中在结束前任意一步失败，那就直接失败了。
        ResponseObj responseObj;
        if (null == file || file.isEmpty()) {
            responseObj = new ResponseObj();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("文件不能为空");
            return new GsonUtils().toJson(responseObj);
        }
        responseObj = new ResponseObj();
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("文件的长度为："+file.getSize());
        return new GsonUtils().toJson(responseObj);
    }
}
