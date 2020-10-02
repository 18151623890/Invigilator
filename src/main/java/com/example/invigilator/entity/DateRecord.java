package com.example.invigilator.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zwz
 * @date 2020/10/2 0:10
 * @description 日期表
 */
@Data
public class DateRecord implements Serializable {

    private static final long serialVersionUID = -7007980230898118494L;

    //主键
    private Integer id;

    //日期时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    //需求总人数
    private Integer total;

    //有效性（0.无效 1.有效）
    private Integer valid;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    //创建人
    private String createName;

    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    //修改人
    private String updateName;


}
