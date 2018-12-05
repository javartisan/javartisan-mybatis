package com.javartisan.service;

import com.javartisan.bean.UserInfo;

import java.util.List;
import java.util.Set;

public interface UserInfoService {

    UserInfo findById(Long id);

    List<UserInfo> selectIdContains(Set<Integer> ids);

    Integer updateById(Long id, String userName, String passWord);

    UserInfo findByIdDollar(String userName);

    Long saveUserInfo(UserInfo userInfo);

    Long saveUserInfoReturnPrimaryKey(UserInfo userInfo);

    Long saveUsers(List<UserInfo> userInfos);

    List<UserInfo> findByOneOfCase(Long id, String userName, String passWord, String userEmail);

    List<UserInfo> findByOneOfCaseUseWhereTag(Long id, String userName, String passWord, String userEmail);

    List<UserInfo> findByOneOfCaseUseTrimTag(Long id, String userName, String passWord, String userEmail);
}
