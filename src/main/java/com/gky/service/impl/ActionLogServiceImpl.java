package com.gky.service.impl;

import com.github.pagehelper.PageHelper;
import com.gky.dao.ActionLogDao;
import com.gky.domain.UserActionLog;
import com.gky.service.ActionLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.net.sdp.SdpSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author Kenny Kam
 * @Date 2019/12/12 19:57
 */
@Service("actionLogService")
public class ActionLogServiceImpl implements ActionLogService {
    @Autowired
    private ActionLogDao actionLogDao;
    private UserActionLog userActionLog;

    public void add(HttpServletRequest request) {
        //获取请求参数集合
        Map<String, String[]> parameterMap = request.getParameterMap();
        String queryStr = "";
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            queryStr += entry.getKey() + "=" + Arrays.toString(entry.getValue()) + "&";
        }
        int a = queryStr.length();
        System.out.println(a);
        userActionLog = new UserActionLog();
        //获取请求方式
        userActionLog.setMethod(request.getMethod());
        //获取请求IP
        if (request.getHeader("x-forwarded-for") == null) {
            userActionLog.setIpAddrV4(request.getRemoteAddr());
        } else {
            userActionLog.setIpAddrV4(request.getHeader("x-forwarded-for"));
        }
        //获取user-agent
        userActionLog.setOther(request.getHeader("User-Agent"));
        //获取用户操作的sessionID，必须
        userActionLog.setSessionId(request.getSession().getId());
        //获取访问的地址
        userActionLog.setDescription(request.getRequestURI());
        //参数集合内容不为空存入数据库
        if (!StringUtils.isBlank(queryStr)) {
            userActionLog.setRequestBody(queryStr);
        }
        actionLogDao.add(userActionLog);
    }


    @Override
    @Deprecated
    /**
     * @Author Kenny Kam
     * @Description 其实在这里我们应该直接调用这个方法来实现功能。毕竟我们的原则是Service层是数据驱动服务。但是我们在这里写，也能实现功能
     * @Date 2019/12/12 20:22
     * @Param [userActionLog]
     * @return void
     */
    public void add(UserActionLog userActionLog) throws Exception {

    }

    @Override
    public List<UserActionLog> findALL(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserActionLog> userActionLogs = actionLogDao.findAll();
        return userActionLogs;
    }
}
