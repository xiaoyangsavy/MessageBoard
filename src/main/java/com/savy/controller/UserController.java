package com.savy.controller;

import com.savy.model.User;
import com.savy.service.UserService;
import com.savy.util.Result;
import com.savy.util.ResultStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;
import java.util.List;

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
    public Result<Integer> register(@RequestParam  String userName,@RequestParam String password,@RequestParam String sex,@RequestParam String phone,@RequestParam String email){
        System.out.println("call /user/register");
        Integer r=0;
        Result<Integer> result=new Result<Integer>();
        if (userName!=""&&userName!=null){
            r=userService.insertUser(userName,password,0,"普通用户",sex,phone,email);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("添加成功！");
            result.setData(r);
        }
        else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("添加失败！");
            result.setData(r);
        }
        return result;
    }

    @RequestMapping(value = "/addUser",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> addUser(@RequestParam String userName,@RequestParam String password,@RequestParam int permissionId){
        System.out.println("call /user/register");
        Integer r=0;
        Result<Integer> result=new Result<Integer>();
        if (userName!=""&&userName!=null){
            r=userService.addUser(userName,password,permissionId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("添加成功！");
            result.setData(r);
        }
        else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("添加失败！");
            result.setData(r);
        }
        return result;
    }
    @RequestMapping(value = "/selectUser",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<User> selectUser(){
        System.out.println("call /user/selectUser");
        List<User> select_User=userService.selectUser();
        System.out.println(select_User.toString());
        return select_User;
    }
    @RequestMapping(value = "/updateUser",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> updateUser(@RequestParam String userName,@RequestParam int userId,@RequestParam  String password,@RequestParam Integer permissionId){
        System.out.println("call /user/updateUser");
        System.out.println("%s"+userName+" "+userId+" "+password+" "+permissionId);
        Integer r=0;
        Result<Integer> result=new Result<Integer>();
        if (userId>0){
            r=userService.updateUser(userName,userId,password,permissionId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("修改用户成功！");
            result.setData(r);
        }
        else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("修改用户失败！");
            result.setData(r);
        }
        return result;
    }
    @RequestMapping(value = "/deleteUser",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> deleteUser(@RequestParam int userId){
        System.out.println("call /user/deleteUser");
        Result<Integer> result=new Result<>();
        Integer r=0;
        if(userId>0){
            r=userService.deleteUser(userId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("删除用户成功！");
            result.setData(r);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("删除用户失败！");
            result.setData(r);
        }
        return result;
    }
    @RequestMapping(value = "/searchPermission",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Integer searchPermission(@RequestParam String userName){
        System.out.println("call /user/searchPermission");
        Integer Permission=userService.searchPermission(userName);
        System.out.println("------------------"+Permission);
        return Permission;

    }
}
