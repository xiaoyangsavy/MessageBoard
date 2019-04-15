package com.savy.dao;

import com.savy.model.User;
import org.apache.ibatis.annotations.Param;

//用户
public interface UserMapper {

    //获取用户id
    Integer getUserId(@Param("userName") String userName, @Param("password") String password);
    //根据id获取用户信息
    User getUserById(@Param("userId") int userId);
    //注册用户
    Integer insertUser(@Param("userName") String userName,@Param("password") String password,@Param("permissionId") Integer permissionid,@Param("name") String name,@Param("sex") String sex,@Param("phone") String phone,@Param("email") String email);
}
