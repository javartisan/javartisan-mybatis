package com.javartisan.mapper;

import com.javartisan.bean.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface UserInfoMapper {

    UserInfo findById(@Param("id") Long id);

    List<UserInfo> selectIdContains(@Param("ids") Set<Integer> ids);

    Integer updateById(@Param("id") Long id, @Param("userName") String userName, @Param("passWord") String passWord);

    UserInfo findByNameDollar(@Param("userName") String userName);

    Long saveUserInfo(@Param("userInfo") UserInfo userInfo);

    Long saveUserInfoReturnPrimaryKey(@Param("userInfo") UserInfo userInfo);

    /**
     * 使用foreach实现动态SQL
     *
     * @param userInfos
     * @return
     */
    Long saveUsers(@Param("userInfos") List<UserInfo> userInfos);

    /**
     * 动态SQL之 choose
     *
     * @param id
     * @param userName
     * @param passWord
     * @param userEmail
     * @return
     */
    List<UserInfo> findByOneOfCase(@Param("id") Long id, @Param("userName") String userName, @Param("passWord") String passWord, @Param("userEmail") String userEmail);


    List<UserInfo> findByOneOfCaseUseWhereTag(@Param("id") Long id, @Param("userName") String userName, @Param("passWord") String passWord, @Param("userEmail") String userEmail);

    List<UserInfo> findByOneOfCaseUseTrimTag(@Param("id") Long id, @Param("userName") String userName, @Param("passWord") String passWord, @Param("userEmail") String userEmail);


}
