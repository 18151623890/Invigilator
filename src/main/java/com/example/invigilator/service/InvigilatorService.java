package com.example.invigilator.service;

import com.example.invigilator.dto.DateDto;
import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;

/**
 * @author zwz
 * @date 2020/10/2 8:29
 * @description 监考业务
 */
public interface InvigilatorService {

    List<DateDto> all();

    List<DateDto> conditionQuery(DateDto dto);

    int addDate(DateDto dto);

}
