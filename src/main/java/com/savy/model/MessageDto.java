package com.savy.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MessageDto {
    private int messageId;//消息编号
    private String typeName;
    private int superMessageId;//父消息编号
    private String messageContent;//消息内容
    private List imageUrl;//图片地址
    private String image_url;
    private List voiceUrl;//音频地址
    private String voice_url;
    private List videoUrl;//视频地址
    private String video_url;
    private int userId;//用户编号
    private double messageGrade;//用户评分
    private boolean isReplay;//管理员是否回复
    private int typeId;
    private String messageTitle;
    private String userName;
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

    public List getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String image_url) {
        String as[]=image_url.split(",");
        this.imageUrl=Arrays.asList(as);
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public List getVoiceUrl() {
        return voiceUrl;
    }

    public void setVoiceUrl(String voice_url) {
        String as[]=voice_url.split(",");
        this.voiceUrl = Arrays.asList(as);
    }

    public String getVoice_url() {
        return voice_url;
    }

    public void setVoice_url(String voice_url) {
        this.voice_url = voice_url;
    }

    public List getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String video_url) {
        String as[]=video_url.split(",");
        this.videoUrl =Arrays.asList(as);
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
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

    public boolean isReplay() {
        return isReplay;
    }

    public void setReplay(boolean replay) {
        isReplay = replay;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
                ", userName='" + userName + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}
