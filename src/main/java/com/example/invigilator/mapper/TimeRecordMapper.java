package com.example.invigilator.mapper;

import com.example.invigilator.dto.DateDto;
import com.example.invigilator.dto.EnlistDto;
import com.example.invigilator.entity.StartTimeAndEndTime;
import com.example.invigilator.entity.TimeRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zwz
 * @date 2020/10/2 10:31
 * @description
 */
@Mapper
public interface TimeRecordMapper {

    //添加时间
    int addTime(List<TimeRecord> list);

    //查询
    List<EnlistDto> all(DateDto dto);

    //是否还有空名额
    int queryQuota(@Param("id") Integer id);

    //删除
    int del(@Param("id") Integer id);

    StartTimeAndEndTime queryTime(@Param("id") Integer id);
}
