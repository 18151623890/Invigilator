package com.example.invigilator.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class User {
    //主键
    private Integer userId;
    //用户名
    private String userName;
    //密码
    private String password;
    //昵称
    private String nickname;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    //创建人
    private String createName;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    //更新人
    private String updateName;
    //有效性（0.无效 1.有效）
    private Integer valid;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public User() {
    }

    public User(Integer userId, String userName, String password, String nickname, Date createTime, String createName, Date updateTime, String updateName, Integer valid) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
        this.createTime = createTime;
        this.createName = createName;
        this.updateTime = updateTime;
        this.updateName = updateName;
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createTime=" + createTime +
                ", createName='" + createName + '\'' +
                ", updateTime=" + updateTime +
                ", updateName='" + updateName + '\'' +
                ", valid=" + valid +
                '}';
    }
}
