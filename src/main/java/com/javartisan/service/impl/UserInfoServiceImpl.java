package com.javartisan.service.impl;

import com.javartisan.bean.UserInfo;
import com.javartisan.mapper.UserInfoMapper;
import com.javartisan.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public List<UserInfo> selectIdContains(Set<Integer> ids) {
        return userInfoMapper.selectIdContains(ids);
    }

    @Override
    public Integer updateById(Long id, String userName, String passWord) {

        return userInfoMapper.updateById(id, userName, passWord);
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

    @Override
    public Long saveUsers(List<UserInfo> userInfos) {
        return userInfoMapper.saveUsers(userInfos);
    }

    @Override
    public List<UserInfo> findByOneOfCase(Long id, String userName, String passWord, String userEmail) {
        return userInfoMapper.findByOneOfCase(id, userName, passWord, userEmail);
    }

    @Override
    public List<UserInfo> findByOneOfCaseUseWhereTag(Long id, String userName, String passWord, String userEmail) {
        return userInfoMapper.findByOneOfCaseUseWhereTag(id, userName, passWord, userEmail);
    }

    @Override
    public List<UserInfo> findByOneOfCaseUseTrimTag(Long id, String userName, String passWord, String userEmail) {
        return userInfoMapper.findByOneOfCaseUseWhereTag(id, userName, passWord, userEmail);
    }


}
