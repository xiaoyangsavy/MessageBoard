package com.savy.service;

import com.savy.dao.MessageMapper;
import com.savy.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;

    public Integer insertMessage(int superMessageId,String messageContent,String messageDate,String imageUrl,String voiceUrl,String videoUrl){
        Integer insert_Message=messageMapper.insertMessage(superMessageId,messageContent,messageDate,imageUrl,voiceUrl,videoUrl);
        return insert_Message;
    }
    public Message selectMessage(String messageDate, String superMessageId,String isReplay, String userId){
        /*System.out.println("selectMessage");
        System.out.println(messageDate!=null?messageDate:"null");
        System.out.printf("%s;%s;%s;%s;",messageDate, superMessageId,isReplay, userId);*/
        Message select_Message=messageMapper.selectMessage(messageDate,superMessageId,isReplay,userId);
        System.out.println(select_Message);
        return select_Message;
    }
}
