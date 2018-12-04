package com.javartisan.mapper;

import com.javartisan.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper {

    UserInfo findById(@Param("id") Long id);

    Long saveUserInfo(@Param("userInfo") UserInfo userInfo);

    Long saveUserInfoReturnPrimaryKey(@Param("userInfo") UserInfo userInfo);
}
