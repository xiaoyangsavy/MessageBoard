package com.savy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MessageEntity extends BaseEntity{
    private int messageId;//消息编号
    private String typeName;
    private String messageContent;//消息内容
    private int typeId;
    private String messageTitle;
    @JsonFormat(pattern="yyyy-MM-dd  HH:mm:ss")
    private Date messageDate;//消息发布时间

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }
}
