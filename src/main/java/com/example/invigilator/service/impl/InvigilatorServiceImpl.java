package com.example.invigilator.service.impl;

import com.example.invigilator.dto.DateDetails;
import com.example.invigilator.dto.DateDto;
import com.example.invigilator.dto.EnlistDto;
import com.example.invigilator.dto.TimeDto;
import com.example.invigilator.entity.DateRecord;
import com.example.invigilator.entity.StartTimeAndEndTime;
import com.example.invigilator.entity.TimeRecord;
import com.example.invigilator.mapper.DateRecordMapper;
import com.example.invigilator.mapper.TimeRecordMapper;
import com.example.invigilator.mapper.UserMapper;
import com.example.invigilator.service.InvigilatorService;
import com.example.invigilator.util.DateTimeUtils;
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
    @Resource
    private UserMapper userMapper;

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

    //问题
    @Override
    public List<DateDetails> byId(Integer id) throws ParseException {
        List<DateDetails> result = new ArrayList<>();
        List<com.example.invigilator.entity.DateDetails> dateDetails = dateRecordMapper.DateDetails(id);
        for (com.example.invigilator.entity.DateDetails detail : dateDetails) {
            DateDetails date = new DateDetails();
            String[] split = detail.getSe().split("\\$");
            date.setStartTime(split[0]);
            date.setEndTime(split[1]);
            date.setRemarks(detail.getRemarks());
            if (detail.getName() != null) {
                date.setNames(detail.getName().split(","));
            }
            result.add(date);
        }
        return result;
    }

    @Override
    public int stop(Integer id) {
        return dateRecordMapper.stop(id);
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

        List<TimeDto> timeList = dto.getTimeList();
        if (timeList == null || timeList.isEmpty()) {
            return 0;
        }
        Integer total = 0;
        for (TimeDto timeDto : timeList) {
            total += timeDto.getTotal();
        }
        dateRecord.setTotal(total);

        dateRecordMapper.addDate(dateRecord);

        Integer id = dateRecord.getId();
        List<TimeRecord> timeRecordList = new ArrayList<>();
        for (TimeDto element : timeList) {
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

    @Override
    public List<EnlistDto> enlist(DateDto dto) {
        return timeRecordMapper.all(dto);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delDate(Integer id) {
        userMapper.del(id);
        timeRecordMapper.del(id);
        dateRecordMapper.del(id);
        return 1;
    }

    @Override
    synchronized
    public int signUp(Integer tid, Integer uid) {
        int quota = timeRecordMapper.queryQuota(tid);
        if (quota == 1) {
            synchronized (this) {
                List<StartTimeAndEndTime> userTime = userMapper.queryTime(uid);
                StartTimeAndEndTime timeInfo = timeRecordMapper.queryTime(tid);
                for (StartTimeAndEndTime time : userTime) {
                    boolean overlap = DateTimeUtils.isOverlap(time.getStartTime(), time.getEndTime(), timeInfo.getStartTime(), timeInfo.getEndTime());
                    if (overlap) {
                        return 0;//存在重复的时间
                    }
                }
                synchronized (this) {
                    int i = timeRecordMapper.queryQuota(tid);
                    if (i == 1) {
                        //添加
                        userMapper.addTime(uid, tid);
                        return 1;
                    } else {
                        return -1;//没有名额
                    }
                }
            }
        } else {
            return -1;//没有名额
        }
    }

    @Override
    public List<StartTimeAndEndTime> exportTime(List<Integer> id) {
        return timeRecordMapper.exportTime(id);
    }

    @Override
    public int unSignUp(Integer uid, Integer tid) {
        return timeRecordMapper.unSignUp(uid, tid);
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
