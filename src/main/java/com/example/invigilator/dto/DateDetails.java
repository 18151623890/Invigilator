package com.example.invigilator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author zwz
 * @date 2020/10/5 17:57
 * @description 日期详情
 */
@Data
public class DateDetails {
    private String startTime;
    private String endTime;

    String[] names;

    private String remarks;


}
