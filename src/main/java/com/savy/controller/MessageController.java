package com.savy.controller;

import com.savy.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/message")
@Controller
public class MessageController {
    @Autowired
    MessageService messageService;
    @RequestMapping(value = "/insertMessage",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Integer insertMessage(@RequestParam String messageContent,@RequestParam String messageDate,@RequestParam String imageUrl,@RequestParam String voiceUrl, @RequestParam String videoUrl){
        System.out.println("call /message/insertMessage");
        Integer integer_message=messageService.insertMessage(messageContent,messageDate,imageUrl,voiceUrl,videoUrl);
        return integer_message;
    }
}
