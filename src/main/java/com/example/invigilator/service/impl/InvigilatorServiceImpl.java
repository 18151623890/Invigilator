package com.example.invigilator.service.impl;

import com.example.invigilator.dto.DateDto;
import com.example.invigilator.dto.timeDto;
import com.example.invigilator.entity.DateRecord;
import com.example.invigilator.entity.TimeRecord;
import com.example.invigilator.mapper.DateRecordMapper;
import com.example.invigilator.mapper.TimeRecordMapper;
import com.example.invigilator.service.InvigilatorService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    @Resource
    private TimeRecordMapper timeRecordMapper;

    @Override
    public List<DateDto> all() {
        List<DateDto> dtoList = new ArrayList<>();

        List<DateRecord> all = dateRecordMapper.all();
        if (all == null)
            return dtoList;

        for (DateRecord dateRecord : all) {
            DateDto dateDto = new DateDto();
            BeanUtils.copyProperties(dateRecord, dateDto);
            dtoList.add(dateDto);
        }
        return dtoList;
    }

    @Override
    public List<DateDto> conditionQuery(DateDto dto) {
        List<DateDto> dtoList = new ArrayList<>();

        List<DateRecord> all = dateRecordMapper.conditionQuery(dto);
        if (all == null)
            return dtoList;
        for (DateRecord dateRecord : all) {
            DateDto dateDto = new DateDto();
            BeanUtils.copyProperties(dateRecord, dateDto);
            dtoList.add(dateDto);
        }
        return dtoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addDate(DateDto dto) {
        DateRecord dateRecord = new DateRecord();
        Date date = dto.getDate();
        dateRecord.setDate(date);

        List<timeDto> timeList = dto.getTimeList();
        if (timeList == null || timeList.isEmpty()) {
            return 0;
        }
        Integer total = 0;
        for (timeDto timeDto : timeList) {
            total += timeDto.getTotal();
        }
        dateRecord.setTotal(total);

        dateRecordMapper.addDate(dateRecord);

        Integer id = dateRecord.getId();
        List<TimeRecord> timeRecordList = new ArrayList<>();
        for (timeDto element : timeList) {
            //处理时间 日期+时间
            element.setStartTime(updateTime(date, element.getStartTime()));
            element.setEndTime(updateTime(date, element.getEndTime()));
            TimeRecord timeRecord = new TimeRecord();
            BeanUtils.copyProperties(element, timeRecord);
            timeRecord.setDid(id);
            timeRecordList.add(timeRecord);
        }

        timeRecordMapper.addTime(timeRecordList);
        return 1;
    }

    private Date updateTime(Date date, Date time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String ymd = format.format(date);
        format = new SimpleDateFormat("HH:mm:ss");
        String sfm = format.format(time);
        String newDate = ymd + " " + sfm;
        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(newDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
