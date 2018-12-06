package com.javartisan.service;

import com.javartisan.bean.Dog;
import com.javartisan.bean.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/spring-config.xml"})
public class UserInfoServiceTest {


    @Autowired
    private UserInfoService userInfoService;


    @Autowired
    private DogService dogService;

    @Test
    public void findById() {
        System.out.println(userInfoService.findById(3L));
    }


    @Test
    public void deleteById() {
        System.out.println(userInfoService.deleteById(31L));
    }


    /**
     * 这是一个反面案例，${fieldName} 是直接替换不会转义
     */
//    @Test
//    public void findByIdDollar() {
//        System.out.println(userInfoService.findByIdDollar("root"));
//    }

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


    @Test
    public void saveUserInfos() {


        List<UserInfo> userInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userInfos.add(new UserInfo("saveUserInfos" + i, System.currentTimeMillis() + "" + i, "saveUserInfos" + i, "saveUserInfos" + i));
        }
        userInfoService.saveUsers(userInfos);
    }


    @Test
    public void findByOneOfCase() {
        List<UserInfo> userInfos = userInfoService.findByOneOfCase(1L, null, null, null);
        System.out.println(userInfos);

        userInfos = userInfoService.findByOneOfCase(null, "root", null, null);
        System.out.println(userInfos);

        userInfos = userInfoService.findByOneOfCase(null, null, null, null);
        System.out.println(userInfos);
    }


    @Test
    public void findByOneOfCaseUseWhereTag() {
        List<UserInfo> userInfos = userInfoService.findByOneOfCaseUseWhereTag(1L, null, null, null);
        System.out.println(userInfos);

        userInfos = userInfoService.findByOneOfCaseUseWhereTag(null, "root", null, null);
        System.out.println(userInfos);

        userInfos = userInfoService.findByOneOfCaseUseWhereTag(null, null, null, null);
        System.out.println(userInfos);
    }


    @Test
    public void findByOneOfCaseUseTrimTag() {
        List<UserInfo> userInfos = userInfoService.findByOneOfCaseUseTrimTag(1L, null, null, null);
        System.out.println(userInfos);

//        userInfos = userInfoService.findByOneOfCaseUseTrimTag(null, "root", null, null);
//        System.out.println(userInfos);
//
//        userInfos = userInfoService.findByOneOfCaseUseTrimTag(null, null, null, null);
//        System.out.println(userInfos);
    }


    @Test
    public void updateById() {
        System.out.println(userInfoService.updateById(3L, "ROOT", "ROOT"));
    }


    @Test
    public void selectIdContains() {
        Set<Integer> ids = new HashSet<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(4);
        System.out.println(userInfoService.selectIdContains(ids));

        Dog dog = new Dog();
        dog.setDogName("diudiu");
        dog.setType("jingba");
        dogService.saveDog(dog);


        System.out.println(dogService.findById(1L));
    }
}
