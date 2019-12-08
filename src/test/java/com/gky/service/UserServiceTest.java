package com.gky.service;

import com.gky.domain.User;
import com.gky.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Author Kenny Kam
 * @Date 2019/12/8 14:54
 */
public class UserServiceTest extends BaseTest {
    @Autowired
    private UserService userService;

    @Test
    public void a() {
        User user = new User();
        String loginId = user.getLoginId();
        try {
            User user1 = userService.findUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}