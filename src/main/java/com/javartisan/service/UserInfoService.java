package com.javartisan.service;

import com.javartisan.bean.UserInfo;

public interface UserInfoService {

    UserInfo findById(Long id);

    Long saveUserInfo(UserInfo userInfo);

    Long saveUserInfoReturnPrimaryKey( UserInfo userInfo);
}
