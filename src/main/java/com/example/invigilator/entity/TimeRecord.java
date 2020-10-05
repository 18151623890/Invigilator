package com.example.invigilator.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zwz
 * @date 2020/10/2 0:14
 * @description 时间表
 */
@Data
public class TimeRecord implements Serializable {

    private static final long serialVersionUID = 1507072733747119394L;

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

    //备注
    private String remarks;

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
