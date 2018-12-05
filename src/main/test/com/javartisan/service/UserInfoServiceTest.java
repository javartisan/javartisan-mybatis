package com.javartisan.service;

import com.javartisan.bean.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-config.xml"})
public class UserInfoServiceTest {


    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void findById() {
        System.out.println(userInfoService.findById(3L));
    }


    /**
     * 这是一个反面案例，${fieldName} 是直接替换不会转义
     */
    @Test
    public void findByIdDollar() {
        System.out.println(userInfoService.findByIdDollar("root"));
    }

    @Test
    public void saveUsreInfo() {
        UserInfo userInfo = new UserInfo();

        userInfo.setUserName(System.currentTimeMillis() + "");
        userInfo.setPassWord(System.currentTimeMillis() + "");
        userInfo.setUser_email("javartisan@163.com");
        System.out.println(userInfoService.saveUserInfo(userInfo));
        System.out.println(userInfo.getId());


    }

    @Test
    public void saveUserInfoReturnPrimaryKey() {
        UserInfo userInfo = new UserInfo();

        userInfo.setUserName(System.currentTimeMillis() + "");
        userInfo.setPassWord(System.currentTimeMillis() + "");
        userInfo.setUser_email("javartisan@163.com");
        System.out.println(userInfoService.saveUserInfoReturnPrimaryKey(userInfo));
        System.out.println(userInfo.getId());


    }
}
