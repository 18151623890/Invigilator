package com.example.invigilator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zwz
 * @date 2020/10/2 0:21
 * @description 日期Dto
 */

@Data
public class DateDto implements Serializable {

    private static final long serialVersionUID = -9016262144431237419L;

    //主键
    private Integer id;

    //日期时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    //总人数
    private Integer total;

    //时间集合
    private List<TimeDto> timeList;

    //日期段-查询用
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endDate;
    //总场数
    private Integer totalTime;

    //有效性
    private Integer valid;


}
