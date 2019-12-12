package com.gky.service;

import com.gky.domain.UserActionLog;

import java.util.List;

/**
 * @Author Kenny Kam
 * @Date 2019/12/12 19:55
 */
public interface ActionLogService extends BaseService<UserActionLog>{
    List<UserActionLog> findALL(int pageNum, int pageSize);

    }
