package com.example.invigilator.service.impl;

import com.example.invigilator.dto.DateDto;
import com.example.invigilator.entity.DateRecord;
import com.example.invigilator.mapper.DateRecordMapper;
import com.example.invigilator.service.InvigilatorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zwz
 * @date 2020/10/2 8:32
 * @description
 */
@Service
public class InvigilatorServiceImpl implements InvigilatorService {

    @Resource
    private DateRecordMapper dateRecordMapper;

    @Override
    public List<DateDto> all() {
        List<DateDto> dtoList = new ArrayList<>();

        List<DateRecord> all = dateRecordMapper.all();
        if (all == null)
            return dtoList;

        for (DateRecord dateRecord : all){
            DateDto dto = new DateDto();
            dto.setId(dateRecord.getId());
            dto.setDate(dateRecord.getDate());
            dto.setTotal(dateRecord.getTotal());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<DateDto> conditionQuery(DateDto dto) {
        List<DateDto> dtoList = new ArrayList<>();

        List<DateRecord> all = dateRecordMapper.conditionQuery(dto);
        if (all == null)
            return dtoList;

        for (DateRecord dateRecord : all){
            DateDto dateDto = new DateDto();
            dateDto.setId(dateRecord.getId());
            dateDto.setDate(dateRecord.getDate());
            dateDto.setTotal(dateRecord.getTotal());
            dtoList.add(dateDto);
        }
        return dtoList;
    }
}
