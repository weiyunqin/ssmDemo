package com.gky.dao;
import java.util.Date;

import com.github.pagehelper.PageHelper;
import com.gky.domain.User;
import com.gky.domain.UserActionLog;
import com.gky.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

/**
 * @Author Kenny Kam
 * @Date 2019/12/8 12:28
 */
public class UserDaoTest extends BaseTest {

    @Autowired
    private UserDao userDao;    //初始化Dao层，面向接口编程
    @Autowired
    private ActionLogDao actionLogDao;    //初始化Dao层，面向接口编程

    /**
     * 添加用户的单元测试，添加成功与否会有对应的提示。
     * 当然按照我这个配置一般会正确，如果说出错就需要你一步一步的看错误的提示代码了。
     * 添加同样的LoginId的用户会添加失败，因为在上面把LoginId作为了数据库表的主键。
     */
    @Test
    public void testAdd() {
//        User user = new User();
//        user.setLoginId("pc147852369");
//        user.setName("雨下一整夜");
//        user.setPwd("123456");
//        user.setSex("未知");
//        int result = 0; //受影响的行数默认为0
//        try {
//            result = userDao.add(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("添加用户失败");
//        }
//        if (result>0)
//            System.out.println("添加用户成功");
        UserActionLog log = new UserActionLog();
        log.setId(0L);
        log.setLoginId("1");
        log.setSessionId("1");
        log.setIpAddrV4("1");
        log.setIpAddrV6("1");
        log.setOsName("1");
        log.setOsVersion("1");
        log.setBroName("1");
        log.setBroVersion("1");
        log.setRequestBody("1");
        log.setDescription("1");
        log.setOther("1");
        log.setMethod("1");
        log.setTime(new Date());


        actionLogDao.add(log);
    }

    /**
     * 查找用户测试，成功与否会有log输出
     */
    @Test
    public void testFindOneId() throws Exception {
        User user = new User();
        user.setLoginId("pc147852369");
        User result = null; //受影响的行数默认为0
        try {
//            result = userDao.findOneById(user.getLoginId());
            PageHelper.startPage(1,5);
            List<UserActionLog> all = actionLogDao.findAll();
            System.out.println("------------------------------------------------------");
            System.out.println(all.size());
//            List<User> all = userDao.findAll();
            System.out.println("------------------------------------------------------");
//            Iterator<User> iterator = all.iterator();
            Iterator<UserActionLog> iterator = all.iterator();
            while (iterator.hasNext()) {
//                User next = iterator.next();
                UserActionLog next = iterator.next();
                System.out.println(next);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查找用户失败");
        }
        if (null!=result)
            System.out.println("查找用户成功\n"+result.toString());
    }

    @Test
    public void testDel() {
        User user = new User();
        user.setLoginId("pc147852369");
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.del(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("删除用户失败");
        }
        if (result>0)
            System.out.println("删除用户成功");
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setLoginId("pc147852369");
        user.setName("手把手教程");
        user.setPwd("123456");
        user.setSex("男");
        int result = 0; //受影响的行数默认为0
        try {
            result = userDao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("更新用户信息用户失败");
        }
        if (result>0)
            System.out.println("更新用户信息用户成功");

    }
}

















































































