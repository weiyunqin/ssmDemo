package com.gky.service.impl;

import com.gky.domain.User;
import org.junit.Test;
import com.gky.test.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author Kenny Kam
 * @Date 2019/12/8 13:40
 */
public class UserServiceImplTest extends BaseTest{
    @Autowired
    //此处直接使用UserService的实现类，主要是方便抛出异常，然后异常出现时候可以针对性的处理
    private UserServiceImpl userService;

    @Test
    public void add() {
        User user = new User();
        try {
            userService.add(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}