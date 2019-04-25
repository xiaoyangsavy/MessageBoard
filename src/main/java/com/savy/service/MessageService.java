package com.savy.service;

import com.savy.dao.MessageMapper;
import com.savy.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;

    public Integer insertMessage(String messageContent,String messageDate,String imageUrl,String voiceUrl,String videoUrl,int typeId){
        Integer insert_Message=messageMapper.insertMessage(messageContent,messageDate,imageUrl,voiceUrl,videoUrl,typeId);
        return insert_Message;
    }
    public Integer insertTypeName(String typeName){
        Integer insert_TypeName=messageMapper.insertTypeName(typeName);
        return insert_TypeName;
    }
    public Integer deleteTypeName(int  typeID) {
        Integer delete_TypeName = messageMapper.deleteTypeName(typeID);
        return delete_TypeName;
    }
    public List<Message> selectMessage(String messageDate, int typeId, String isReplay, String userId){
        List<Message> select_Message=messageMapper.selectMessage(messageDate,typeId,isReplay,userId);
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




