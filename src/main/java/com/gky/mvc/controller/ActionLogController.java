package com.gky.mvc.controller;

import com.gky.domain.ResponseObj;
import com.gky.domain.UserActionLog;
import com.gky.service.ActionLogService;
import com.gky.utils.GsonUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author Kenny Kam
 * @Date 2019/12/12 20:31
 */
@Controller
@RequestMapping("/actionLog")
public class ActionLogController {
    @Autowired
    ActionLogService actionLogService;

    @RequestMapping(value = "/findLogList"
            , produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object findLog(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize) {
        if (pageNum <= 0) {
            //错误页码默认跳转到第一页
            pageNum = 1;
        }
        if (pageSize <= 0) {
            //错误数据长度默认设置为10条
            pageSize = 10;
        }
        List<UserActionLog> result = actionLogService.findALL(pageNum, pageSize);
        ResponseObj<UserActionLog> responseObj = new ResponseObj<>();
        if (CollectionUtils.isEmpty(result)) {
            responseObj.setCode(ResponseObj.EMPTY);
            responseObj.setMsg("查询结果为空");
            return new GsonUtils().toJson(responseObj);
        }
        responseObj.setCode(ResponseObj.OK);
        responseObj.setMsg("查询成功");
        responseObj.setData(result);
        return new GsonUtils().toJson(responseObj);
    }
}
