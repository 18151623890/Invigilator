package com.example.invigilator.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author zwz
 * @date 2020/10/3 21:43
 * @description 统计日期内有几时间
 */
@Data
public class DateTotal {

    private Integer total;

    //日期
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

}
