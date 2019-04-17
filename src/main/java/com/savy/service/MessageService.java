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
}
