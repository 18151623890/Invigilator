package com.example.invigilator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zwz
 * @date 2020/10/2 0:19
 * @description 时间Dto
 */

@Data
public class TimeDto implements Serializable {

    private static final long serialVersionUID = -4367665248923214171L;

    //主键
    private Integer id;

    //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;

    //结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;

    //需求总人数
    private Integer total;

}
