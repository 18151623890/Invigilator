package com.example.invigilator.mapper;

import com.example.invigilator.dto.DateDto;
import com.example.invigilator.entity.DateRecord;
import org.apache.ibatis.annotations.Mapper;

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


}
