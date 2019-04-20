package com.savy.controller;

import com.savy.model.Message;
import com.savy.service.MessageService;
import com.savy.util.Result;
import com.savy.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/message")
@Controller
public class MessageController {
    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/insertMessage",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> insertMessage(@RequestParam String messageContent,@RequestParam String messageDate,@RequestParam String imageUrl,@RequestParam String voiceUrl, @RequestParam String videoUrl,@RequestParam int typeId){
        System.out.println("call /message/insertMessage");
        Result<Integer> result=new Result<Integer>();
        Integer r=0;
        if(messageContent!=""&&messageContent!=null)
        {
            r=messageService.insertMessage(messageContent,messageDate,imageUrl,voiceUrl,videoUrl,typeId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("添加成功！");
            result.setData(r);
        }
        else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("添加失败！");
            result.setData(r);
        }
        return result;
    }

    @RequestMapping(value = "/selectMessage",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Message> selectMessage(@RequestParam String messageDate, @RequestParam int typeId, @RequestParam String isReplay, @RequestParam String userId){
        System.out.println("call /message/selectMessage");
        List<Message> select_Message=messageService.selectMessage(messageDate,typeId,isReplay,userId);
        System.out.println(select_Message.toString());
        return select_Message;
    }
    @RequestMapping(value = "/selectProblem",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
   public String selectProblem(@RequestParam int superMessageId){
        System.out.println("call /message/selectProblem");
        String select_Problem=messageService.selectProblem(superMessageId);
        System.out.println(select_Problem);
        return select_Problem;
    }

}
