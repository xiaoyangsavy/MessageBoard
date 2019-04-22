package com.savy.dao;

import org.apache.ibatis.annotations.Param;

public interface MessageMapper {
    //新增问题
    Integer insertMessage(@Param("messageContent") String messageContent, @Param("messageDate") String messageDate, @Param("imageUrl") String imageUrl, @Param("voiceUrl") String voiceUrl, @Param("videoUrl") String videoUrl);
    Integer insertMessage_type(@Param("typeName") String typeName );
    Integer deleteMessage_type(@Param("typeID") int typeID );
}



