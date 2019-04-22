package com.savy.dao;

import com.savy.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//用户
public interface UserMapper {

    //获取用户id
    Integer getUserId(@Param("userName") String userName, @Param("password") String password);
    //根据id获取用户信息
    User getUserById(@Param("userId") int userId);
    //注册用户
    Integer insertUser(@Param("userName") String userName,@Param("password") String password,@Param("permissionId") Integer permissionid,@Param("name") String name,@Param("sex") String sex,@Param("phone") String phone,@Param("email") String email);
    //用户管理——添加用户
    Integer addUser(@Param("userName") String userName,@Param("password") String password,@Param("permissionId") Integer permissionId);
    //用户管理——查询用户
    List<User> selectUser();
    //用户管理——修改用户
    Integer updateUser(@Param("userName") String userName,@Param("userId") int userId,@Param("password") String password,@Param("permissionId") Integer permissionId);
    //用户管理——删除用户
    Integer deleteUser(@Param("userId") int userId);

}
