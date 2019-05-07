package com.savy.controller;
import com.github.pagehelper.PageHelper;
import com.savy.model.*;
import com.savy.service.MessageService;
import com.savy.service.UserService;
import com.savy.util.Result;
import com.savy.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/message")
@Controller
public class MessageController {
    @Autowired
    MessageService messageService;


    @RequestMapping(value = "/insertMessage",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> insertMessage(@RequestParam String messageContent,
                                         //@RequestParam String messageDate,
                                         //@RequestParam String imageUrl,
                                         @RequestParam(name = "imageUrl",required = false) MultipartFile imageUrl,
                                         @RequestParam(name = "voiceUrl",required = false) MultipartFile voiceUrl,
                                         @RequestParam(name = "videoUrl",required = false) MultipartFile videoUrl,
                                         @RequestParam int typeId,
                                         @RequestParam String messageTitle,
                                         @RequestParam Integer userId){
        System.out.println("call /message/insertMessage");
        String imageUrl_2="",voiceUrl_2="",videoUrl_2="";
        try {

            if(!imageUrl.isEmpty()){
            imageUrl_2="/"+imageUrl.getOriginalFilename();
            messageService.up(imageUrl);
        }
        if(!voiceUrl.isEmpty()){
            voiceUrl_2="/"+voiceUrl.getOriginalFilename();
            messageService.up(voiceUrl);
        }
        if (!videoUrl.isEmpty()){
            videoUrl_2="/"+videoUrl.getOriginalFilename();
            messageService.up(videoUrl);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
        Result<Integer> result=new Result<Integer>();
        Integer r=0;
        if(messageContent!=""&&messageContent!=null)
        {
            r=messageService.insertMessage(messageContent,imageUrl_2,voiceUrl_2,videoUrl_2,typeId,messageTitle,userId);
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
    @Deprecated
    public Result<List<Message>> selectMessage( @RequestParam(name = "startDate", required = false) String startDate,
                                        @RequestParam(name = "endDate",required = false) String endDate,
                                        @RequestParam(name = "typeId", required = false) Integer typeId,
                                        @RequestParam(name = "isReplay", required = false) String isReplay,
                                        @RequestParam(name = "userName", required = false) String userName,
                                        @RequestParam(name="messageTitle",required = false) String messageTitle){
        System.out.println("call /message/selectMessage");
        Result<List<Message>> result=new Result<>();
        List<Message> select_Message=messageService.selectMessage(startDate,endDate,typeId,isReplay,userName,messageTitle);
        System.out.println(select_Message.toString());
        result.setResultStatus(ResultStatus.SUCCESS);
        result.setMessage("调用成功！");
        result.setData(select_Message);
        return result;
    }
    @RequestMapping(value = "/selectTypeName",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<List<MessageType>> selectTypeName(){
        System.out.println("call /message/selectTypeName");
        Result<List<MessageType>> result=new Result<>();
        List<MessageType> select_TypeName=messageService.selectTypeName();
        result.setResultStatus(ResultStatus.SUCCESS);
        result.setMessage("调用成功！");
        result.setData(select_TypeName);
        return result;
    }
    @RequestMapping(value = "/addReply",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> addReply(@RequestParam int superMessageId,@RequestParam  String messageContent,@RequestParam String messageDate,@RequestParam String imageUrl,@RequestParam String voiceUrl,@RequestParam String videoUrl,@RequestParam int userId)
    {
        System.out.println("call /message/addReply");
        Result<Integer> result=new Result<>();
        Integer r=0;
        boolean isReplay=false;
        int permisstion=messageService.searchPermission(userId);
        if(permisstion!=0){
            isReplay=true;
        }
        if(messageContent!=""&&messageContent!=null)
        {
            r=messageService.addReply(superMessageId,messageContent,messageDate,imageUrl,voiceUrl,videoUrl,userId,isReplay);
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

    @RequestMapping(value = "/insertTypeName",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> insertTypeName(@RequestParam String typeName) {
        System.out.println("call /message/insertTypeName");
        Result<Integer> result=new Result<>();
        Integer r=0,count;
        count=messageService.select_Type(typeName);
        if(typeName!=null&&typeName!=""&&count==0)
        {
            r= messageService.insertTypeName(typeName);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("插入类型名成功！");
            result.setData(r);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("插入类型名失败！");
            result.setData(r);
        }
        return result;
    }
    @RequestMapping(value = "/deleteTypeName",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> deleteMessage_type(@RequestParam Integer typeId) {
        System.out.println("call /message/deleteTypeName");
        Result<Integer> result=new Result<>();
        Integer r=0,count;
        count=messageService.countMessageType(typeId);
        if(typeId > 0&&count==0)
        {
            r=messageService.deleteTypeName(typeId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("删除信息类型成功！");
            result.setData(r);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("删除信息类型失败！");
            result.setData(r);
        }
        return  result;
    }

    @RequestMapping(value = "/updateTypeName",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> update_TypeName(@RequestParam String typeName,@RequestParam Integer typeId){
        System.out.println("call /message/updateTypeName");
        Result<Integer> result=new Result<>();
        result.setResultStatus(ResultStatus.SUCCESS);
        result.setMessage("修改信息类别成功！");
        result.setData(messageService.updateTypeName(typeName,typeId));
        return result;
    }

    @RequestMapping(value = "/viewProblem",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<List<Message>> viewProblem(@RequestParam int superMessageId){
        System.out.println("call /message/viewProblem");
        Result<List<Message>> result=new Result<>();
        List<Message> view_Problem=messageService.viewProblem(superMessageId);
        if(view_Problem.size() > 0)
        {
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("接口调用成功！");
            result.setData(view_Problem);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("接口调用失败！");
        }
        return result;
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
//采用分页插件实现
    @RequestMapping(value = "/queryMessageList",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<PageEntity> queryList(
                                @RequestParam(name = "startDate", required = false) String startDate,
                                @RequestParam(name = "endDate",required = false) String endDate,
                                @RequestParam(name = "typeId", required = false) Integer typeId,
                                @RequestParam(name = "isReplay", required = false) String isReplay,
                                @RequestParam(name = "userName", required = false) String userName,
                                @RequestParam(name="messageTitle",required = false) String messageTitle,
                                @RequestParam(name = "currentPage",required =false)  Integer currentPage,
                                @RequestParam(name = "pageSize",required = false) Integer pageSize){
        System.out.println("call /message/queryList");
        if(currentPage==null||currentPage==0){
            currentPage=1;
        }
        if(pageSize==null||pageSize==0){
            pageSize=10;
        }
        int start=(currentPage-1)*pageSize;
        int end=pageSize;
        Result<PageEntity> result=new Result<>();
        result.setResultStatus(ResultStatus.SUCCESS);
        result.setMessage("查询成功！");
        result.setData(messageService.findItemByPage(startDate,endDate,typeId,isReplay,userName,messageTitle,currentPage,pageSize,start,end));
        //PageEntity pageEntity=messageService.findItemByPage(startDate,endDate,typeId,isReplay,userName,messageTitle,currentPage,pageSize,start,end);
        return result;
    }

    @RequestMapping(value = "/selectMessageGrade",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> selectMessageGrade(@RequestParam Integer messageId){
        System.out.println("call /message/selectMessageGrade");
        Integer select_MessageGrade=messageService.selectMessageGrade(messageId);
        Result<Integer> result=new Result<>();
        result.setResultStatus(ResultStatus.SUCCESS);
        result.setMessage("查询评分成功！");
        result.setData(select_MessageGrade);
        return result;
    }


}



