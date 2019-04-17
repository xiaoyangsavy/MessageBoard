package com.savy.controller;

import com.savy.model.User;
import com.savy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

@RequestMapping(value = "/user")
@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login() {
        System.out.println("call /user/login");
        Integer userId = userService.getUserId("admin","admin");
        return String.valueOf(userId);
    }

    @RequestMapping(value = "/getUser")
    @ResponseBody
    public User getUser() {
        System.out.println("call /user/getUser");
        User user = userService.getUserById(1);
        System.out.println(user.toString());
        return user;
    }
    @RequestMapping(value = "/register",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Integer register(@RequestParam  String userName,@RequestParam String password,@RequestParam String sex,@RequestParam String phone,@RequestParam String email){
        System.out.println("call /user/register");
        Integer r=userService.insertUser(userName,password,0,"普通用户",sex,phone,email);
        return r;
    }
   // @RequestMapping(value = "/")

}
