package com.savy.service;

import com.savy.dao.UserMapper;
import com.savy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//用户
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public Integer getUserId(String userName,String password){
        Integer userId = userMapper.getUserId(userName,password);
        return userId;
    }

    public User getUserById(int userId){
        User user = userMapper.getUserById(userId);
        return user;
    }
}
