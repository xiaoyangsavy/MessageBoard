package com.savy.service;

import com.savy.dao.MessageMapper;
import com.savy.model.Message;
import com.savy.model.MessageEntity;
import com.savy.model.MessageType;
import com.savy.model.PageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;

    public Integer insertMessage(String messageContent,String messageDate,String imageUrl,String voiceUrl,String videoUrl,int typeId,String messageTitle){
        Integer insert_Message=messageMapper.insertMessage(messageContent,messageDate,imageUrl,voiceUrl,videoUrl,typeId,messageTitle);
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
    public List<Message> selectMessage(String startDate,String endDate, Integer typeId, String isReplay, String userName,String messageTitle){
        List<Message> select_Message=messageMapper.selectMessage(startDate,endDate,typeId,isReplay,userName,messageTitle);
        System.out.println(select_Message);
        return select_Message;
    }
    public Integer addReply(int superMessageId,String messageContent,String messageDate,String imageUrl,String voiceUrl,String videoUrl,int userId){
        Integer add_Reply=messageMapper.addReply(superMessageId,messageContent,messageDate,imageUrl,voiceUrl,videoUrl,userId);
        return  add_Reply;

    }
    public List<Message> viewProblem(int superMessageId){
        List<Message> view_Problem=messageMapper.viewProblem(superMessageId);
        System.out.println(view_Problem);
        return view_Problem;
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
}




