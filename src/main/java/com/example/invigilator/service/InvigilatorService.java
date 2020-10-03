package com.example.invigilator.service;

import com.example.invigilator.dto.DateDto;
import com.example.invigilator.dto.EnlistDto;
import com.example.invigilator.entity.StartTimeAndEndTime;

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

    List<EnlistDto> enlist(DateDto dto);

    int delDate(Integer id);

    int signUp(Integer tid,Integer uid);

    List<StartTimeAndEndTime> exportTime(List<Integer> id);
}
