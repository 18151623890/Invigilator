package com.example.invigilator.mapper;

import com.example.invigilator.dto.DateDto;
import com.example.invigilator.entity.DateRecord;
import com.example.invigilator.entity.DateTotal;
import com.example.invigilator.entity.StartTimeAndEndTime;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zwz
 * @date 2020/10/2 8:33
 * @description
 */
@Mapper
public interface DateRecordMapper {

    //查询所有
    List<DateRecord> all();

    //条件查询
    List<DateRecord> conditionQuery(DateDto dto);

    //添加日期
    int addDate(DateRecord dateRecord);

    //删除
    int del(@Param("id") Integer id);

    //停止报名
    int stop(@Param("id") Integer id);

    List<DateTotal> exportDate(@Param("ids")List<Integer> ids);
}
