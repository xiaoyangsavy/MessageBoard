package com.savy.service;

import com.savy.dao.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;

    public Integer insertMessage(String messageContent,String messageDate,String imageUrl,String voiceUrl,String videoUrl){
        Integer insert_Message=messageMapper.insertMessage(messageContent,messageDate,imageUrl,voiceUrl,videoUrl);
        return insert_Message;
    }
    public Integer insertMessage_type(String type_name){
        Integer insert_Message_type=messageMapper.insertMessage_type(messageName);
        return insert_Message_type;
    }
    public Integer deleteMessage_type(int  typeID){
        Integer delete_Message_type=messageMapper.deteleMessage_type(typeID);
        return delete_Message_type;
    }
}




