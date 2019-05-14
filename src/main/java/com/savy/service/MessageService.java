package com.savy.service;

import com.alibaba.druid.util.StringUtils;
import com.savy.dao.MessageMapper;
import com.savy.model.Message;
import com.savy.model.MessageDto;
import com.savy.model.MessageEntity;
import com.savy.model.MessageType;
import com.savy.model.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;

    public Integer insertMessage(String messageContent, String imageUrl, String voiceUrl, String videoUrl, int typeId, String messageTitle, Integer userId){
        Date messageDate=new Date();
        Integer insert_Message=messageMapper.insertMessage(messageContent,messageDate,imageUrl,voiceUrl,videoUrl,typeId,messageTitle,userId);
        return insert_Message;
    }
    public Integer insertMessage_2(String messageContent, String imageUrl, String voiceUrl, String videoUrl, int typeId, String messageTitle, Integer userId, Integer superMessageId,boolean isReplay){
        Date messageDate=new Date();
        Integer insert_Message=messageMapper.insertMessage_2(messageContent,messageDate,imageUrl,voiceUrl,videoUrl,typeId,messageTitle,userId,superMessageId,isReplay);
        return insert_Message;
    }
    public List<MessageType> selectTypeName(){
        List<MessageType> select_TypeName=messageMapper.selectTypeName();
        return select_TypeName;
    }


    public MessageType select_TypeName(Integer typeId){
        MessageType  select_TypeName=messageMapper.selectType_Name(typeId);
        return select_TypeName;
    }
    public Integer select_Type(String typeName){
        Integer select_Type=messageMapper.select_Type(typeName);
        return select_Type;
    }
    public Integer insertTypeName(String typeName){
        Integer insert_TypeName=messageMapper.insertTypeName(typeName);
        return insert_TypeName;
    }
    public Integer deleteTypeName(int  typeId) {
        Integer delete_TypeName = messageMapper.deleteTypeName(typeId);
        return delete_TypeName;
    }

    public Integer updateTypeName(String typeName,Integer typeId){
        Integer update_TypeName=messageMapper.updateTypeName(typeName,typeId);
        return update_TypeName;
    }
    public Integer countMessageType(Integer typeId){
        Integer count_MessageType=messageMapper.countMessageType(typeId);
        return count_MessageType;
    }

    public List<Message> selectMessage(String startDate,String endDate, Integer typeId, String isReplay, String userName,String messageTitle){
        List<Message> select_Message=messageMapper.selectMessage(startDate,endDate,typeId,isReplay,userName,messageTitle);
        System.out.println(select_Message);
        return select_Message;
    }
    public Integer addReply(int superMessageId,String messageContent,Date messageDate,String imageUrl,String voiceUrl,String videoUrl,int userId,boolean isReplay){
        Integer add_Reply=messageMapper.addReply(superMessageId,messageContent,messageDate,imageUrl,voiceUrl,videoUrl,userId,isReplay);
        return  add_Reply;
    }



    public List<MessageDto> viewProblem(int superMessageId){
        List<MessageDto> view_Problem=messageMapper.viewProblem(superMessageId);
        System.out.println(view_Problem);
        return view_Problem;
    }

    public Integer searchPermission(int userId){
        Integer search_Permission=messageMapper.searchPermission(userId);
        return search_Permission;
    }

    public Integer deleteProblem(int messageId){
        Integer delete_Problem=messageMapper.deleteProblem(messageId);
        return  delete_Problem;
    }
    public  Integer deleteReply(int superMessageId){
        Integer deleteReply=messageMapper.deleteReply(superMessageId);
        return deleteReply;
    }
    public Integer addMessageGrade(double messageGrade,int messageId){
        Integer add_MessageGrade=messageMapper.addMessageGrade(messageGrade,messageId);
        return add_MessageGrade;
    }

    public Integer selectMessageGrade(Integer messageId){
        Integer select_MessageGrade=messageMapper.selectMessageGrade(messageId);
        return select_MessageGrade;
    }

    public PageEntity findItemByPage(String startDate,String endDate, Integer typeId, String isReplay, String userName,String messageTitle,Integer currentPage,Integer pageSize,int start,int end) {
        int Total=0;
        int count=messageMapper.messageCount(startDate,endDate,typeId,isReplay,userName,messageTitle);
        if(messageMapper.messageCount(startDate,endDate,typeId,isReplay,userName,messageTitle)%pageSize>=0)
        {
            if(messageMapper.messageCount(startDate,endDate,typeId,isReplay,userName,messageTitle)%pageSize==0){
                Total=(messageMapper.messageCount(startDate,endDate,typeId,isReplay,userName,messageTitle)/pageSize);
            }
            else {
                Total=(messageMapper.messageCount(startDate,endDate,typeId,isReplay,userName,messageTitle)/pageSize)+1;
            }

        }
        //System.out.println("-----------------------"+Total);
        if(currentPage>Total){
            currentPage=Total;
        }
        if((count-currentPage*pageSize)<0){
            end=count-(currentPage-1)*pageSize;
        }
        List<Message> messageEntityList = messageMapper.selectMessage_page(startDate,endDate,typeId,isReplay,userName,messageTitle,start,end);        //全部商品
        PageEntity<Message> pageEntity=new PageEntity<>(messageEntityList);
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
    //单文件上传
    public String up(MultipartFile file,String childFiled){
        // List<MultipartFile> files=(Mul)(request)
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        //  System.out.println("---------------------"+path);
        String p=StringUtils.subString(path,"","MessageBoard");
        //System.out.println("---------------------"+p);
        path=p+"\\filed\\"+childFiled;
        File f = new File(path);
        if(!f.exists()&&!f.isDirectory()){
            f.mkdirs();
            //System.out.println("创建文件");
        }else {
            //System.out.println("文件夹已经存在");
        }
        try{
            if(file.isEmpty()){
                return "文件为空";
            }
            String fileName = file.getOriginalFilename();

            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName=rid();
            //System.out.println("-----------------------"+fileName);
            //String filePath = "E:/test_load/";
            String filePath = f.toString()+"\\";
            path = filePath + fileName+suffixName;
            //System.out.println("--------------------------"+path);
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return path;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }
    //多文件上传
    public String up2(MultipartFile[] files,String childFiled,String newPath) {
        //List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        //List fide_path=new ArrayList();
        String fide_path="";
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        /*String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
          System.out.println("0---------------------"+path);

        String myPath = "";
        myPath = this.getClass().getResource("").getPath();
        System.out.println("1---------------------"+myPath);

        try {
            myPath = new File("").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("2---------------------"+myPath);

        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println("3---------------------"+xmlpath);

        System.out.println("4---------------------"+System.getProperty("java.class.path"));*/

      //  String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
//          System.out.println("0---------------------"+path);

//        String myPath = "";
//        myPath = this.getClass().getResource("").getPath();
//        System.out.println("1---------------------"+myPath);
//
//        try {
//            myPath = new File("").getCanonicalPath();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("2---------------------"+myPath);
//
//        URL xmlpath = this.getClass().getClassLoader().getResource("");
//        System.out.println("3---------------------"+xmlpath);
//
//        System.out.println("4---------------------"+System.getProperty("java.class.path"));




         String p=StringUtils.subString(newPath,"","message");
        //String p=StringUtils.subString(path,"","MessageBoard");
       // System.out.println("---------------------"+p);
        String path=p+"Files\\"+childFiled;
        System.out.println("++++++++++++++++++++++++++++++++++++"+path);
        File f = new File(path);
        if(!f.exists()&&!f.isDirectory()){
            f.mkdirs();
            System.out.println("创建文件");
        }else {
            System.out.println("文件夹已经存在");
        }
        for (int i = 0; i < files.length; ++i) {
            file = files[i];
            //String filePath = "E:/";
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            fileName=rid();
            String filePath = path+"/";
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + fileName+suffixName)));//设置文件路径及名字
                    stream.write(bytes);// 写入
                    stream.close();
                   // String pp=(filePath + fileName+suffixName).substring(1);//截取字符串（从1下标开始）
                   // System.out.println(StringUtils.subString(pp,"Files",pp.length()));
                    String pp="Files/"+childFiled+"/"+fileName+suffixName;
                    fide_path=fide_path+pp+",";
                    //System.out.println("-------------------------"+path+fileName+suffixName);
                } catch (Exception e) {
                    stream = null;
                    /*return "第 " + i + " 个文件上传失败 ==> "
                            + e.getMessage();*/
                }
            } else {
                /*return "第 " + i
                        + " 个文件上传失败因为文件为空";*/
            }
        }
        return fide_path;
    }
    public Integer updateMessageGrade(double messageGrade,Integer messageId){
        Integer update_MessageGrade=messageMapper.updateMessageGrade(messageGrade,messageId);
        return update_MessageGrade;
    }
    //生成UUID
    public String tid(){
        Date date=new Date();
        String tid=String.valueOf(date.getYear()+date.getMonth()+date.getDay()+date.getHours()+date.getMinutes());
        return tid;
    }
    public String rid(){
       String uuid5 = UUID.randomUUID().toString();
        return uuid5;
    }

    public Integer updateExitReplay(Integer messageId,boolean exitReply){
        Integer update_ExitReplay=messageMapper.updateExitReplay(exitReply,messageId);
        return update_ExitReplay;
    }


}




