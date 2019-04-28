package com.savy.service;

import com.savy.dao.MessageMapper;
import com.savy.model.Message;
import com.savy.model.MessageType;
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
    public List<Message> selectMessage(String messageDate,String endDate, Integer typeId, String isReplay, String userId,String messageTitle){
        List<Message> select_Message=messageMapper.selectMessage(messageDate,endDate,typeId,isReplay,userId,messageTitle);
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
}




