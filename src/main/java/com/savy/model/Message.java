package com.savy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Message {
    private int messageId;//消息编号
    private String typeName;
    private int superMessageId;//父消息编号
    private String messageContent;//消息内容
    private String imageUrl;//图片地址
    private String voiceUrl;//音频地址
    private String videoUrl;//视频地址
    private int userId;//用户编号
    private double messageGrade;//用户评分
    private int isReplay;//管理员是否回复
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

    public int getSuperMessageId() {
        return superMessageId;
    }

    public void setSuperMessageId(int superMessageId) {
        this.superMessageId = superMessageId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getMessageGrade() {
        return messageGrade;
    }

    public void setMessageGrade(double messageGrade) {
        this.messageGrade = messageGrade;
    }

    public int getIsReplay() {
        return isReplay;
    }

    public void setIsReplay(int isReplay) {
        this.isReplay = isReplay;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", typeName='" + typeName + '\'' +
                ", superMessageId=" + superMessageId +
                ", messageContent='" + messageContent + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", voiceUrl='" + voiceUrl + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", userId=" + userId +
                ", messageGrade=" + messageGrade +
                ", isReplay=" + isReplay +
                ", typeId=" + typeId +
                ", messageTitle='" + messageTitle + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}
