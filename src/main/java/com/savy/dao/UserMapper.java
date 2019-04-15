package com.savy.dao;

import com.savy.model.User;
import org.apache.ibatis.annotations.Param;

//用户
public interface UserMapper {

    //获取用户id
    Integer getUserId(@Param("userName") String userName, @Param("password") String password);
    //根据id获取用户信息
    User getUserById(@Param("userId") int userId);
}
