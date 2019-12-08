package com.gky.service.impl;

import com.gky.dao.UserDao;
import com.gky.domain.User;
import com.gky.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Kenny Kam
 * @Date 2019/12/8 13:23
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    /**
     * 添加用户，一般来说需要检查用户为空、用户名为空、密码为空
     */
    public void add(User user) throws Exception {
        //先检查用户是否存在
        if (null == user) {
            throw new Exception("用户不能为空");
        }
        //用户名不能为空检查
        if (StringUtils.isBlank(user.getName())) {
            throw new Exception("用户名不能为空");

        }
        //用户密码不能为空检查
        if (StringUtils.isBlank(user.getPwd())) {
            throw new Exception("用户密码不能为空");
        }
        //已经存在相同用户
        if (null != userDao.findOneById(user.getLoginId())) {
            throw new Exception("已经存在相同用户");
        }
        //受影响的行数默认为0
        int result;
        try {
            result = userDao.add(user);
        } catch (Exception e) {
            System.out.println("添加用户失败,用户已经存在");
            //其他用户添加失败异常
            throw new Exception(e);
        }
        if (result > 0) {
            System.out.println("添加用户成功");
        }

    }

    @Override
    /**
     * 查找用户
     *
     * @param user 用户bean
     * @throws Exception
     */
    public User findUser(User user) {
        return userDao.findOneById(user.getLoginId());
    }
}
