package com.savy.service;

import com.savy.dao.UserMapper;
import com.savy.model.PageEntity;
import com.savy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Integer insertUser(String userName,String password,int permissionId,String name,String sex,String phone,String email){
        Integer insert_user=userMapper.insertUser(userName,password,permissionId,name,sex,phone,email);
        return insert_user;
    }

    public Integer addUser(String userName,String password,int permissionId){
        Integer add_User=userMapper.addUser(userName,password,permissionId);
        return  add_User;
    }
    public PageEntity selectUser(Integer currentPage,Integer pageSize,int start,int end){
        int Total=0;
        int count=userMapper.userCount();
        if(userMapper.userCount()%pageSize>0)
        {
            Total=(userMapper.userCount()/pageSize)+1;
        }
        if(currentPage>Total){
            currentPage=Total;
        }
        if((count-currentPage*pageSize)<0){
            end=count-(currentPage-1)*pageSize;
        }
        List<User> select_User=userMapper.selectUser(start,end);
        PageEntity<User> pageEntity=new PageEntity<>(select_User);
        if (currentPage==1){
            pageEntity.setIsFirstPage(true);
        }
        if (currentPage==Total){
            pageEntity.setIsLastPage(true);
        }
        pageEntity.setCurrentPage(currentPage);//当前页
        pageEntity.setTotal(count);//总记录数
        pageEntity.setPages(Total);//总页数

        return pageEntity;
    }
    public Integer updateUser(String userName,int userId,String password,Integer permissionId){
        Integer update_User=userMapper.updateUser(userName,userId,password,permissionId);
        return update_User;
    }

    public Integer deleteUser(int userId){
        Integer delete_User=userMapper.deleteUser(userId);
        return  delete_User;
    }
    public Integer searchPermission(String userName){
        Integer search_Permission=userMapper.searchPermission(userName);
        return search_Permission;
    }
    public boolean login(String userName,String password){
        boolean flag = false;
        Integer count=userMapper.login(userName,password);
        if(count==1){
            flag = true;
        }
        return flag;
    }
    public Integer selectID(String userName){
        Integer select_ID=userMapper.selectID(userName);
        return select_ID;
    }

}
