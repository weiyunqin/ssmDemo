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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author Kenny Kam
 * @Date 2019/12/8 14:00
 */
@Controller
@RequestMapping(value = "/userAction")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/reg"
            , method = RequestMethod.POST
            , produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Object reg(HttpServletRequest req, HttpServletResponse resp, HttpSession session, User user) throws Exception {
        Object result;
        ResponseObj responseObj;
        if (null == user) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户信息不能为空！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        if (StringUtils.isEmpty(user.getLoginId()) || StringUtils.isEmpty(user.getPwd())) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        User user1 = userService.findUser(user);
        if (null != user1) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户已经存在！");
            result = new GsonUtils().toJson(responseObj);
            return result;
        } else {
            try {
                userService.add(user);
            } catch (Exception e) {
                e.printStackTrace();
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("其他错误！");
                result = new GsonUtils().toJson(responseObj);
                return result;
            }
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.OK);
            responseObj.setMsg("注册成功");
            //单独设置密码为sessionId 误导黑客，前端访问服务器的时候必须有这个信息才能操作
            user.setPwd(session.getId());
            //单独控制地址
            user.setNextUrl(req.getContextPath() + "/mvc/home");
            responseObj.setData(user);
            //将一些基本信息写入到session中
            session.setAttribute("userInfo", user);
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
    }

    @RequestMapping(value = "/login"
            , method = RequestMethod.POST
            , produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public Object login(HttpServletRequest req, HttpServletResponse resp, HttpSession session, User user) throws Exception {
        Object result;
        ResponseObj responseObj;
        if (null == user) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("登录信息不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        if (StringUtils.isEmpty(user.getLoginId()) || StringUtils.isEmpty(user.getPwd())) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.FAILED);
            responseObj.setMsg("用户名或密码不能为空");
            result = new GsonUtils().toJson(responseObj);
            return result;
        }
        //查找用户
        User user1 = userService.findUser(user);
        if (null == user1) {
            responseObj = new ResponseObj<User>();
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("未找到该用户");
            result = new GsonUtils().toJson(responseObj);
        } else {
            if (user.getPwd().equals(user1.getPwd())) {
                user1.setPwd(session.getId());
                user1.setNextUrl(req.getContextPath() + "/mvc/home");
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.OK);
                responseObj.setMsg(ResponseObj.OK_STR);
                responseObj.setData(user1);
                result = new GsonUtils().toJson(responseObj);
            } else {
                responseObj = new ResponseObj<User>();
                responseObj.setCode(ResponseObj.FAILED);
                responseObj.setMsg("用户密码错误");
                result = new GsonUtils().toJson(responseObj);
            }
        }
        return result;
    }

    @RequestMapping(value = "/uploadHeadPic", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    public Object uploadHeadPic(@RequestParam(required = false) MultipartFile file, HttpServletRequest req) {
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
        responseObj.setMsg("文件的长度为：" + file.getSize());
        return new GsonUtils().toJson(responseObj);
    }
}
