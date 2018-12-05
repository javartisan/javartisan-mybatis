package com.javartisan.service.impl;

import com.javartisan.bean.UserInfo;
import com.javartisan.mapper.UserInfoMapper;
import com.javartisan.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private UserInfoMapper userInfoMapper;

    //  mapper的代理类：MapperProxy
    @Override
    public UserInfo findById(Long id) {
        return userInfoMapper.findById(id);
    }

    @Override
    public UserInfo findByIdDollar(String userName) {
        return userInfoMapper.findByNameDollar(userName);
    }

    @Override
    public Long saveUserInfo(UserInfo userInfo) {
        return userInfoMapper.saveUserInfo(userInfo);
    }

    @Override
    public Long saveUserInfoReturnPrimaryKey(UserInfo userInfo) {
        return userInfoMapper.saveUserInfoReturnPrimaryKey(userInfo);
    }
}
