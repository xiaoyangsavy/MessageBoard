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

    @RequestMapping(value = "/selectMessage",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Message> selectMessage(@RequestParam String messageDate, @RequestParam int typeId, @RequestParam String isReplay, @RequestParam String userId){
        System.out.println("call /message/selectMessage");
        List<Message> select_Message=messageService.selectMessage(messageDate,typeId,isReplay,userId);
        System.out.println(select_Message.toString());
        return select_Message;
    }
    @RequestMapping(value = "/addReply",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> addReply(@RequestParam int superMessageId,@RequestParam  String messageContent,@RequestParam String messageDate,@RequestParam String imageUrl,@RequestParam String voiceUrl,@RequestParam String videoUrl,@RequestParam int userId)
    {
        System.out.println("call /message/addReply");
        Result<Integer> result=new Result<>();
        Integer r=0;
        if(messageContent!=""&&messageContent!=null)
        {
            r=messageService.addReply(superMessageId,messageContent,messageDate,imageUrl,voiceUrl,videoUrl,userId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("回复成功！");
            result.setData(r);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("回复失败！");
            result.setData(r);
        }
        return result;

    }
    @RequestMapping(value = "/viewProblem",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List<Message> viewProblem(@RequestParam int superMessageId){
        System.out.println("call /message/viewProblem");
        List<Message> view_Problem=messageService.viewProblem(superMessageId);
        System.out.println(view_Problem.toString());
        return view_Problem;
    }
    @RequestMapping(value = "/deleteProblem",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> deleteProblem(@RequestParam int messageId){
        System.out.println("call /message/deleteProblem");
        Result<Integer> result=new Result<>();
        Integer r=0;
        if(messageId > 0)
        {
            r=messageService.deleteProblem(messageId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("删除问题成功！");
            result.setData(r);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("删除问题失败！");
            result.setData(r);
        }
        return  result;
    }
    @RequestMapping(value = "/deleteReply",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> deleteReply(@RequestParam int superMessageId){
        System.out.println("call /message/deleteReply");
        Result<Integer> result=new Result<>();
        Integer r=0;
        if (superMessageId>0)
        {
            r=messageService.deleteReply(superMessageId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("删除回复成功！");
            result.setData(r);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("删除回复失败！");
            result.setData(r);
        }
        return  result;
    }
    @RequestMapping(value = "/addMessageGrade",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> addMessageGrade(@RequestParam double messageGrade,@RequestParam int messageId){
        System.out.println("call /message/addMessageGrade");
        Result<Integer> result=new Result<>();
        Integer r=0;
        if(messageId>0)
        {
            r=messageService.addMessageGrade(messageGrade,messageId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("评分成功！");
            result.setData(r);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("评分失败！");
            result.setData(r);
        }
        return result;
    }

}
