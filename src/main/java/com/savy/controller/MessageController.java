package com.savy.controller;

import com.savy.model.Message;
import com.savy.service.MessageService;
import com.savy.util.Result;
import com.savy.util.ResultStatus;
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
    public Result<Integer> insertMessage(@RequestParam int superMessageId,@RequestParam String messageContent,@RequestParam String messageDate,@RequestParam String imageUrl,@RequestParam String voiceUrl, @RequestParam String videoUrl){
        System.out.println("call /message/insertMessage");
        Result<Integer> result=new Result<Integer>();
        Integer r=messageService.insertMessage(superMessageId,messageContent,messageDate,imageUrl,voiceUrl,videoUrl);
        if (r!=0){
            result.setResultStatus(ResultStatus.SUCCESS);
        }
        return result;
    }

    @RequestMapping(value = "/selectMessage",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Message selectMessage(@RequestParam String messageDate,@RequestParam String superMessageId,@RequestParam String isReplay,@RequestParam String userId){
        System.out.println("call /message/selectMessage");
        Message select_Message=messageService.selectMessage(messageDate,superMessageId,isReplay,userId);
        System.out.println(select_Message.toString());
        return select_Message;
    }
}
