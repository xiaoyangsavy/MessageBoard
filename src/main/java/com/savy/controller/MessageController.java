package com.savy.controller;
import com.savy.model.*;
import com.savy.service.MessageService;
import com.savy.util.Result;
import com.savy.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.List;

@RequestMapping(value = "/message")
@Controller
public class MessageController {
    @Autowired
    MessageService messageService;


    @RequestMapping(value = "/insertMessage",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> insertMessage(@RequestParam String messageContent,
                                         @RequestParam(value="imageFile", required=false) MultipartFile[] imageFile,
                                         @RequestParam(value="voiceFile", required=false) MultipartFile[] voiceFile,
                                         @RequestParam(value="videoFile", required=false) MultipartFile[] videoFile,
                                         @RequestParam(name = "messageId",required= false) Integer messageId,
                                         @RequestParam Integer typeId,
                                         @RequestParam String messageTitle,
                                         @RequestParam Integer userId,
                                         HttpServletRequest request){
        System.out.println("call /message/insertMessage");
        System.out.println("userId:"+userId);
       // System.out.println("================================================="+"messageContent"+messageContent+"++++++typeId"+typeId+"messageTitle"+messageTitle+"userId"+userId+"messageId:"+messageId);
        Result<Integer> result=new Result<Integer>();
        String newPath=request.getRealPath("/");

        System.out.println(request.getRealPath("/"));  //1.8已使用   request.getServletContext().getRealPath("/")
        String imageUrl_2="",voiceUrl_2="",videoUrl_2="";
        int superMessageId=0;
        boolean isReplay=false;

       try {
           if(imageFile.length>0){
            imageUrl_2=messageService.up2(imageFile,"image",newPath);
        }
        if(voiceFile.length>0){
            voiceUrl_2=messageService.up2(voiceFile,"voice",newPath);
        }
        if(videoFile.length>0){
            videoUrl_2=messageService.up2(videoFile,"video",newPath);
        }
        }catch (Exception e){
            e.printStackTrace();
           result.setResultStatus(ResultStatus.FAIL);
           result.setMessage("添加信息失败！");
           return result;
        }
        Integer r=0;
        if((messageId!=null)&&(messageContent!="")&&(messageContent!=null)){//用户评论的插入
            superMessageId=messageId;
            isReplay=true;
          //  boolean exitReplay=true;
            //messageService.updateExitReplay(messageId,exitReplay);
            r=messageService.insertMessage_2(messageContent,imageUrl_2,voiceUrl_2,videoUrl_2,typeId,messageTitle,userId,superMessageId,isReplay);
            boolean exitReply=true;
            int uid=messageService.searchPermission(userId);
            if(uid!=0){
                messageService.updateExitReplay(messageId,exitReply);
            }
            if(r>0){
                result.setResultStatus(ResultStatus.SUCCESS);
                result.setMessage("添加信息成功！");
                result.setData(r);

            }else {
                result.setResultStatus(ResultStatus.FAIL);
                result.setMessage("添加信息失败！");
            }
            return result;
        }
        if(((messageContent!="")||(messageContent!=null))&&((messageTitle!="")&&(messageTitle!=null)))//发布信息
        {
            r=messageService.insertMessage(messageContent,imageUrl_2,voiceUrl_2,videoUrl_2,typeId,messageTitle,userId);
            //System.out.println("================================================="+r+"messageContent"+messageContent+"++++++"+"imageUrl_2"+imageUrl_2+"voiceUrl_2"+voiceUrl_2+"videoUrl_2"+videoUrl_2+"typeId"+typeId+"messageTitle"+messageTitle+"userId"+userId+"messageId:"+messageId);
            if(r>0){
                result.setResultStatus(ResultStatus.SUCCESS);
                result.setMessage("添加信息成功！");
                result.setData(r);

            }else {
                result.setResultStatus(ResultStatus.FAIL);
                result.setMessage("添加信息失败！");
            }
            return result;
        }
      /*   else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("添加信息失败！");
            //result.setData(r);
        }
       if(r>0){
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("添加信息成功！");
            //result.setData(r);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("添加信息失败！");
            //result.setData(r);
        }
       if((messageContent==null ||messageContent=="")&&(messageId==null)&&(typeId==null)&&(messageTitle==null||messageTitle=="")&&(userId==null)&&(imageFile.length==0)&&(videoFile.length==0)&&(voiceFile.length==0)){
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("添加信息失败！");
            return result;
        }
        if((messageTitle==""||messageTitle==null)&&(messageContent==""||messageContent==null)){
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("添加信息失败！");
            return result;
        }
        if(r==0||r==null){
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("添加信息失败！");
            return result;
        }*/
        return result;
    }
    @RequestMapping(value = "/insert_Message_2",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> insert_Message_2(@RequestParam("file") MultipartFile file,
                                            HttpServletRequest request,
                                            @RequestParam("messageId") Integer messageId){
        Result<Integer> result=new Result<Integer>();
        String newPath=request.getRealPath("/");
        String imagUrl=null,voiceUrl=null ,videoUrl=null;
        System.out.println(request.getRealPath("/"));  //1.8已使用   request.getServletContext().getRealPath("/")
        String path=messageService.up(file,"",newPath);//image//voice//video
        messageService.up_message(imagUrl,voiceUrl,videoUrl,messageId);
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
        result.setMessage("查询信息成功！");
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
        result.setMessage("查询消息类型成功！");
        result.setData(select_TypeName);
        return result;
    }


    @RequestMapping(value = "/selectTypeById",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<MessageType> selectTypeById(@RequestParam Integer typeId){
        System.out.println("call /message/selectTypeName");
        Result<MessageType> result=new Result<>();
        MessageType messageType=messageService.select_TypeName(typeId);
        result.setResultStatus(ResultStatus.SUCCESS);
        result.setMessage("查询消息类型成功！");
        result.setData(messageType);
        return result;
    }






    @RequestMapping(value = "/addReply",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> addReply(@RequestBody Map<String,Object> myMap)
    {
        System.out.println("call /message/addReply");
        int superMessageId=(Integer)myMap.get("superMessageId");
        String messageContent=String.valueOf(myMap.get("messageContent"));
        String imageUrl=String.valueOf(myMap.get("imageUrl"));
        String voiceUrl=String.valueOf(myMap.get("voiceUrl"));
        String videoUrl=String.valueOf(myMap.get("videoUrl"));
        int userId=(Integer) myMap.get("userId");
        Date messageDate=new Date();
        Result<Integer> result=new Result<>();
        Integer r=0;
        boolean isReplay=true;
        boolean exitReplay=true;
        int permisstion=messageService.searchPermission(userId);
        /*if(permisstion!=0){
            isReplay=true;
        }*/
        if(messageContent!=""&&messageContent!=null)
        {
            messageService.updateExitReplay(superMessageId,exitReplay);
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
    public Result<Integer> insertTypeName(@RequestBody Map<String,String> myMap) {
        System.out.println("call /message/insertTypeName");
        String typeName=myMap.get("typeName");
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
    public Result<Integer> deleteMessage_type(@RequestBody Map<String,Object> myMap) {
        System.out.println("call /message/deleteTypeName");
        Integer typeId=Integer.valueOf(myMap.get("typeId").toString());
        Result<Integer> result=new Result<>();
        Integer r=0,count,userPermissin=0;
        count=messageService.countMessageType(typeId);
        userPermissin=messageService.userPermissin(typeId);
        if(userPermissin>0){
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("有相关类型的管理员，删除信息类型失败！");
            return result;
        }
        if(typeId > 0&&count==0&&userPermissin==0)
        {
            r=messageService.deleteTypeName(typeId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("删除信息类型成功！");
            result.setData(r);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("有相关类型的信息，删除信息类型失败！");
        }
        return  result;
    }

    @RequestMapping(value = "/updateTypeName",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> update_TypeName(@RequestBody Map<String,Object> myMap){
        System.out.println("call /message/updateTypeName");
        String typeName=String.valueOf(myMap.get("typeName"));
        Integer typeId=(Integer)myMap.get("typeId");
        Result<Integer> result=new Result<>();
        result.setResultStatus(ResultStatus.SUCCESS);
        result.setMessage("修改信息类别成功！");
        result.setData(messageService.updateTypeName(typeName,typeId));
        return result;
    }

    @RequestMapping(value = "/viewProblem",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<List<MessageDto>> viewProblem(@RequestParam int superMessageId){
        System.out.println("call /message/viewProblem");
        Result<List<MessageDto>> result=new Result<>();
        List<MessageDto> view_Problem=messageService.viewProblem(superMessageId);
        if(view_Problem.size()> 0)
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
    public Result<Integer> deleteProblem(@RequestBody Map<String,Integer> myMap){
        System.out.println("call /message/deleteProblem");
        int messageId=myMap.get("messageId");
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
    public Result<Integer> deleteReply(@RequestBody Map<String,Integer> myMap){
        System.out.println("call /message/deleteReply");
        int superMessageId=myMap.get("superMessageId");
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
    public Result<Integer> addMessageGrade(@RequestBody Map<String,Object> myMap){
        System.out.println("call /message/addMessageGrade");
        double messageGrade=(Double)myMap.get("messageGrade");
        int messageId=(Integer)myMap.get("messageId");
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
                                @RequestParam(name = "exitReplay",required = false) String exitReplay,
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
        result.setMessage("接口调用成功！");
        result.setData(messageService.findItemByPage(startDate,endDate,typeId,isReplay,exitReplay,userName,messageTitle,currentPage,pageSize,start,end));
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
    @RequestMapping(value = "/test",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String test(){
       // messageService.rid();
        return messageService.rid();
    }
    @RequestMapping(value="fileupload", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer>  addPic(
                       @RequestParam(value="file", required=false) MultipartFile[] file)  {
        //System.out.println(file.getOriginalFilename());
        System.out.println(file.length);
        for(int i=0;i<file.length;i++){

            System.out.println(file[i].getOriginalFilename());
        }
        Result<Integer> result=new Result<>();

        result.setResultStatus(ResultStatus.SUCCESS);
        result.setMessage("上传成功！");
        result.setData(1);
        return result;
//        return "success";
    }
    @RequestMapping(value = "/updateMessageGrade",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> updateMessageGrade(@RequestBody Map<String,Object> map){
        System.out.println("call /message/updateMessageGrade");
        double messageGrade=Double.valueOf(map.get("messageGrade").toString());
        int messageId=(Integer)map.get("messageId");
        Result<Integer> result=new Result<>();
        Integer update_MessageGrade=messageService.updateMessageGrade(messageGrade,messageId);
        if (update_MessageGrade>0){
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("更新评论成功！");
            result.setData(update_MessageGrade);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("更新评论失败");
        }
        return result;

    }
}



