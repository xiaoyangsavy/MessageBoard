package com.savy.service;

import com.alibaba.druid.util.StringUtils;
import com.savy.dao.MessageMapper;
import com.savy.model.Message;
import com.savy.model.MessageEntity;
import com.savy.model.MessageType;
import com.savy.model.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;

    public Integer insertMessage(String messageContent, String imageUrl, String voiceUrl, String videoUrl, int typeId, String messageTitle, Integer userId){
     //   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
       // System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
        Date messageDate=new Date();
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String p=StringUtils.subString(path,"","MessageBoard");
        if(imageUrl!=""&&imageUrl!=null){
            imageUrl=imageUrl.substring(imageUrl.lastIndexOf("/"));
            imageUrl=p+"filed"+imageUrl;
            imageUrl=imageUrl.substring(1,imageUrl.length());
        }
        if(voiceUrl!=""&&voiceUrl!=null){
            voiceUrl=voiceUrl.substring(voiceUrl.lastIndexOf("/"));
            voiceUrl=p+"filed"+voiceUrl;
            voiceUrl=voiceUrl.substring(1,voiceUrl.length());
        }
        if(videoUrl!=""&&videoUrl!=null){
            videoUrl=videoUrl.substring(videoUrl.lastIndexOf("/"));
            videoUrl=p+"filed"+videoUrl;
            videoUrl=videoUrl.substring(1,videoUrl.length());
        }
        Integer insert_Message=messageMapper.insertMessage(messageContent,messageDate,imageUrl,voiceUrl,videoUrl,typeId,messageTitle,userId);
        return insert_Message;
    }
    public List<MessageType> selectTypeName(){
        List<MessageType> select_TypeName=messageMapper.selectTypeName();
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
    public Integer addReply(int superMessageId,String messageContent,String messageDate,String imageUrl,String voiceUrl,String videoUrl,int userId,boolean isReplay){
        Integer add_Reply=messageMapper.addReply(superMessageId,messageContent,messageDate,imageUrl,voiceUrl,videoUrl,userId,isReplay);
        return  add_Reply;

    }
    public List<Message> viewProblem(int superMessageId){
        List<Message> view_Problem=messageMapper.viewProblem(superMessageId);
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

    public PageEntity findItemByPage(String startDate,String endDate, Integer typeId, String isReplay, String userName,String messageTitle,Integer currentPage,Integer pageSize,int start,int end) {
        int Total=0;
        int count=messageMapper.messageCount(startDate,endDate,typeId,isReplay,userName,messageTitle);
        if(messageMapper.messageCount(startDate,endDate,typeId,isReplay,userName,messageTitle)%pageSize>0)
        {
            Total=(messageMapper.messageCount(startDate,endDate,typeId,isReplay,userName,messageTitle)/pageSize)+1;
        }
        if(currentPage>Total){
            currentPage=Total;
        }
        if((count-currentPage*pageSize)<0){
            end=count-(currentPage-1)*pageSize;
        }
        List<MessageEntity> messageEntityList = messageMapper.selectMessage_page(startDate,endDate,typeId,isReplay,userName,messageTitle,start,end);        //全部商品
        PageEntity<MessageEntity> pageEntity=new PageEntity<>(messageEntityList);
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
    public String up(MultipartFile file){
        // List<MultipartFile> files=(Mul)(request)
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        //  System.out.println("---------------------"+path);
        String p=StringUtils.subString(path,"","MessageBoard");
        //System.out.println("---------------------"+p);
        path=p+"\\filed";
        File f = new File(path);
        if(!f.exists()&&!f.isDirectory()){
            f.mkdirs();
            //System.out.println("创建文件");
        }else {
            //System.out.println("文件夹已经存在");
        }
        // System.out.println("--------------------"+f);
        try{
            if(file.isEmpty()){
                return "文件为空";
            }
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //String filePath = "E:/test_load/";
            String filePath = f.toString()+"//";
            path = filePath + fileName;
            File dest = new File(path);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();// 新建文件夹
            }
            file.transferTo(dest);// 文件写入
            return "上传成功";
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }


}




