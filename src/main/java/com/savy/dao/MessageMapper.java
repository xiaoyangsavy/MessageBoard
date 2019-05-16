package com.savy.dao;

import com.savy.model.Message;
import com.savy.model.MessageDto;
import com.savy.model.MessageEntity;
import com.savy.model.MessageType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MessageMapper {
    //新增问题
    //Integer insertMessage(@Param("messageContent") String messageContent, @Param("messageDate") String messageDate, @Param("imageUrl") String imageUrl, @Param("voiceUrl") String voiceUrl, @Param("videoUrl") String videoUrl);
    //添加类别名称
    Integer insertTypeName(@Param("typeName") String typeName );
    //删除类别
    Integer deleteTypeName(@Param("typeId") int typeId );
    //查找信息类型
     List<MessageType> selectTypeName();

    //查找信息类型
   MessageType  selectType_Name(@Param("typeId") Integer typeId);
    //根据信息名查找信息
    Integer select_Type(@Param("typeName") String typeName);
    //修改类别
    Integer updateTypeName(@Param("typeName") String typeName,@Param("typeId") Integer typeId);
    //根据信息类别查找信息个数
    Integer countMessageType(@Param("typeId") Integer typeId);

    Integer searchPermission(@Param("userId") Integer userId);



    //新增问题
    Integer insertMessage(@Param("messageContent") String messageContent, @Param("messageDate") Date messageDate, @Param("imageUrl") String imageUrl, @Param("voiceUrl") String voiceUrl, @Param("videoUrl") String videoUrl, @Param("typeId") int typeId, @Param("messageTitle") String messageTitle , @Param("userId") Integer userId);
    Integer insertMessage_2(@Param("messageContent") String messageContent, @Param("messageDate") Date messageDate, @Param("imageUrl") String imageUrl, @Param("voiceUrl") String voiceUrl, @Param("videoUrl") String videoUrl, @Param("typeId") int typeId, @Param("messageTitle") String messageTitle , @Param("userId") Integer userId,@Param("superMessageId") Integer superMessageId,@Param("isReplay") boolean isReplay);
    //查询问题
    List<Message> selectMessage(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("typeId") Integer typeId, @Param("isReplay") String isReplay, @Param("userName") String userName,@Param("messageTitle") String messageTitle);
    //回复问题
    Integer addReply(@Param("superMessageId") int superMessageId,@Param("messageContent") String messageContent,@Param("messageDate") Date messageDate,@Param("imageUrl") String imageUrl,@Param("voiceUrl") String voiceUrl,@Param("videoUrl") String videoUrl,@Param("userId") int userId,@Param("isReplay") boolean isReplay);
    //查看问题
    List<MessageDto> viewProblem(@Param("superMessageId") int superMessageId);
    //删除问题
    Integer deleteProblem(@Param("messageId") int messageId);
    //删除回复
    Integer deleteReply(@Param("superMessageId") int superMessageId);
    //添加评分
    Integer addMessageGrade(@Param("messageGrade") double messageGrade,@Param("messageId") int messageId);
    //查找评分
    Integer selectMessageGrade(@Param("messageId") Integer messageId);
    //修改评分
    Integer updateMessageGrade(@Param("messageGrade") double messageGrade,@Param("messageId") Integer messageId);
    //信息是否被回复
    Integer updateExitReplay(@Param("exitReply") boolean exitReply,@Param("messageId") Integer messageId);

    Integer userPermissin(@Param("typeId") Integer typeId);

    Integer up_message(@Param("imagUrl") String  imagUrl,@Param("voiceUrl") String voiceUrl,@Param("videoUrl") String videoUrl,@Param("messageId") Integer messageId);

    //查询问题个数
    Integer messageCount(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("typeId") Integer typeId, @Param("isReplay") String isReplay,@Param("exitReplay") String exitReplay, @Param("userName") String userName,@Param("messageTitle") String messageTitle);
    List<Message> selectMessage_page(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("typeId") Integer typeId, @Param("isReplay") String isReplay,@Param("exitReplay") String exitReplay, @Param("userName") String userName,@Param("messageTitle") String messageTitle,@Param("start") Integer start,@Param("end") Integer end);

    //更新ExitReply
    Integer updateExitReply(@Param("exitReplay") String exitReplay,@Param("messageId") Integer messageId);
    //查找用户的permission
    Integer selectUserPermission(@Param("userId") Integer userId);
}



