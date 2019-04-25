package com.savy.dao;

import com.savy.model.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    //新增问题
    Integer insertMessage(@Param("messageContent") String messageContent, @Param("messageDate") String messageDate, @Param("imageUrl") String imageUrl, @Param("voiceUrl") String voiceUrl, @Param("videoUrl") String videoUrl);
    //添加类别名称
    Integer insertTypeName(@Param("typeName") String typeName );
    //删除类别
    Integer deleteTypeName(@Param("typeID") int typeID );

    Integer insertMessage(@Param("messageContent") String messageContent, @Param("messageDate") String messageDate, @Param("imageUrl") String imageUrl, @Param("voiceUrl") String voiceUrl, @Param("videoUrl") String videoUrl,@Param("typeId") int typeId );
    //查询问题
    List<Message> selectMessage(@Param("messageDate") String messageData, @Param("typeId") int typeId, @Param("isReplay") String isReplay, @Param("userId") String userId);
    //回复问题
    Integer addReply(@Param("superMessageId") int superMessageId,@Param("messageContent") String messageContent,@Param("messageDate") String messageDate,@Param("imageUrl") String imageUrl,@Param("voiceUrl") String voiceUrl,@Param("videoUrl") String videoUrl,@Param("userId") int userId);
    //查看问题
    List<Message> viewProblem(@Param("superMessageId") int superMessageId);
    //删除问题
    Integer deleteProblem(@Param("messageId") int messageId);
    //删除回复
    Integer deleteReply(@Param("superMessageId") int superMessageId);
    //添加评论
    Integer addMessageGrade(@Param("messageGrade") double messageGrade,@Param("messageId") int messageId);
}



