package com.savy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

//用户
public class User {

//(user_id,user_name,password,role_id,contribution,name,sex,phone,email,last_login_date)
    private int userId; //用户编号
    private String userName;   //用户名
    private String password;   //密码
    //private String roleId;   //角色编号
    private String name;   //姓名
    private String sex;   //性别
    private String phone;   //手机号
    private String email;   //邮箱
    private Integer permissionId;//用户权限
    private String permissionName;
    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss")
    private Date lastLoginDate;   //最后登录日期


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   /* public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", permissionId=" + permissionId +
                ", permissionName='" + permissionName + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                '}';
    }
}
