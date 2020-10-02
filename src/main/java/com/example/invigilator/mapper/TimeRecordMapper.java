package com.example.invigilator.mapper;

import com.example.invigilator.entity.TimeRecord;
import org.apache.ibatis.annotations.Mapper;

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
}
