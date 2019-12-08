package com.gky.service;

import com.gky.domain.User;

/**
 * @Author Kenny Kam
 * @Date 2019/12/8 13:20
 */
public interface UserService extends BaseService<User> {

    User findUser(User user) throws Exception;
}
