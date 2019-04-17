package com.savy.dao;

import com.savy.model.Message;
import org.apache.ibatis.annotations.Param;

public interface MessageMapper {
    //新增问题
    Integer insertMessage(@Param("superMessageId") int superMessageId,@Param("messageContent") String messageContent, @Param("messageDate") String messageDate, @Param("imageUrl") String imageUrl, @Param("voiceUrl") String voiceUrl, @Param("videoUrl") String videoUrl);
    //查询问题
    Message selectMessage(@Param("messageDate") String messageData,@Param("superMessageId") String superMessageId,@Param("isReplay") String isReplay,@Param("userId") String userId);
}
