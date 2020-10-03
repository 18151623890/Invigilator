package com.example.invigilator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zwz
 * @date 2020/10/3 9:00
 * @description
 */
@Data
public class EnlistDto implements Serializable {

    private static final long serialVersionUID = -6347946371758142530L;

    //主键
    private Integer id;

    //日期主键
    private Integer did;

    //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    //结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    //需求总人数
    private Integer total;

    //日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    //已参加人数
    private Integer joinTotal;

    //是否报名--（相对登录方） 0.报名  1.未报名
    private Integer flag;

}
