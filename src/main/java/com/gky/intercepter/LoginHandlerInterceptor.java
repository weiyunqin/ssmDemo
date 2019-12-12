package com.gky.intercepter;

import com.gky.service.ActionLogService;
import com.gky.service.impl.ActionLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author Kenny Kam
 * @Date 2019/12/9 21:21
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter {
    /**
     * 不对匹配该值的访问路径拦截（正则）
     */
    String NO_INTERCEPTOR_PATH = ".*/((login)|(reg)|(logout)|(code)|(app)|(home)|(weixin)|(static)|(main)|(websocket)|(findLogList)|(uploadHeadPic)).*";
    @Autowired
    ActionLogServiceImpl service;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getServletPath();
        //不包含静态资源和登陆注册的请求
        if (!path.matches(".*/((static)|(login)|(reg)).*")) {
            service.add(request);
        }
        if (path.matches(NO_INTERCEPTOR_PATH)) {
            return true;
        } else {
            //不匹配的进行处理
            try {
                //session中是否存在用户信息，不存在则是未登录状态
                if (request.getSession().getAttribute("userInfo") == null) {
                    response.sendRedirect(request.getContextPath() + "/mvc/login");
                    return false;
                }
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/mvc/login");
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
