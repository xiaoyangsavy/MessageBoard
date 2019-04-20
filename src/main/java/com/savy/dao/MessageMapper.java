package com.savy.dao;

import com.savy.model.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    //新增问题
    Integer insertMessage(@Param("messageContent") String messageContent, @Param("messageDate") String messageDate, @Param("imageUrl") String imageUrl, @Param("voiceUrl") String voiceUrl, @Param("videoUrl") String videoUrl,@Param("typeId") int typeId );
    //查询问题
    List<Message> selectMessage(@Param("messageDate") String messageData, @Param("typeId") int typeId, @Param("isReplay") String isReplay, @Param("userId") String userId);
    //查询问题
    String selectProblem(@Param("superMessageId") int superMessageId);
    //回复问题
    Integer updateReply(@Param("messageContent") String messageContent,@Param("messageId") int messageId);
}
