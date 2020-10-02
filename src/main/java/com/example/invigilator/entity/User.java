package com.example.invigilator.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
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
}
