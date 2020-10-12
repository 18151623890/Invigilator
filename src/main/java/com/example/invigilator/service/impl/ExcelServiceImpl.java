package com.example.invigilator.service.impl;

import com.example.invigilator.entity.DateTotal;
import com.example.invigilator.entity.NicknameTid;
import com.example.invigilator.entity.StartTimeAndEndTime;
import com.example.invigilator.entity.UserHaveTime;
import com.example.invigilator.mapper.DateRecordMapper;
import com.example.invigilator.mapper.TimeRecordMapper;
import com.example.invigilator.mapper.UserMapper;
import com.example.invigilator.service.ExcelService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zwz
 * @date 2020/10/3 21:47
 * @description
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Resource
    private DateRecordMapper dateRecordMapper;
    @Resource
    private TimeRecordMapper timeRecordMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public HSSFWorkbook export(List<Integer> dateId) {
        List<DateTotal> dateTotals = dateRecordMapper.exportDate(dateId);

        List<StartTimeAndEndTime> timeLists = timeRecordMapper.exportTime(dateId);

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("监考报名单");

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, timeLists.size()));
        HSSFCell cell = sheet.createRow(0).createCell(0);
        cell.setCellValue("监考报名单");
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
        HSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("姓名");
        Integer row = 2;
        Integer cells = 2;
        int start = cells - 1;
        int end = 0;

        for (DateTotal dateTotal : dateTotals) {
            Integer len = dateTotal.getTotal();
            end = start + len - 1;
            if (start == end) {
                row1.createCell(start).setCellValue(formatDate(dateTotal.getDate()));
                start = end + 1;
            } else {
                sheet.addMergedRegion(new CellRangeAddress(row - 1, row - 1, start, end));
                row1.createCell(start).setCellValue(formatDate(dateTotal.getDate()));
                start = end + 1;
            }
        }
        //填写时间
        Map<String, Integer> timeMap = new HashMap();
        HSSFRow row2 = sheet.createRow(2);
        int i = 1;
        for (StartTimeAndEndTime time : timeLists) {
            timeMap.put(time.getId().toString(), i);
            String remarks = (time.getRemarks().isEmpty()) ? "" : "(" + time.getRemarks() + ")";
            row2.createCell(i++).setCellValue(formatTime(time.getStartTime()) + " -- " + formatTime(time.getEndTime()) + "(" + time.getTotal() + "人)" + remarks);
            queryNotJoin(workbook,time);
        }

        int rows = 3;

        List<UserHaveTime> users = new LinkedList<>();
//        List<UserHaveTime> users = userMapper.exportUser(dateId);
        List<NicknameTid> userTid = userMapper.exportUser(dateId);
        Set<String> setNickname = new HashSet<>();
        for (NicknameTid nicknameTid : userTid){
            setNickname.add(nicknameTid.getNickname());
        }


        Iterator<String> iterator = setNickname.iterator();
        while (iterator.hasNext()){
            String nickname = iterator.next();
            UserHaveTime userHaveTime = new UserHaveTime();
            userHaveTime.setNickname(nickname);
            for (NicknameTid nicknameTid : userTid){
                if (nicknameTid.getNickname().equals(nickname))
                    userHaveTime.addTid(nicknameTid.getTid());
            }
            users.add(userHaveTime);
        }


        for (UserHaveTime userHaveTime : users) {
            boolean flag = true;
            HSSFRow row3 = sheet.createRow(rows);
            row3.createCell(0).setCellValue(userHaveTime.getNickname());
            //row3.createCell(0).setCellValue(userHaveTime.getNickname());
            String tid = userHaveTime.getTid();
            String[] split = tid.split(",");//所有报名的时间
            for (int j = 0; j < split.length; j++) {
                String s = split[j];
                Integer cel = timeMap.get(s);
                if (cel == null) {//t t
                    continue;
                } else {
                    flag = false;
                    row3.createCell(cel).setCellValue("√");
                }
            }
            if (!flag) {
                rows++;
            }
        }
        return workbook;
    }

    //查询时间内没有参加的所有人，并产生一张表
    private void queryNotJoin(HSSFWorkbook workbook,StartTimeAndEndTime time){
        String remarks = (time.getRemarks().isEmpty()) ? "" : "(" + time.getRemarks() + ")";
        HSSFSheet sheet = workbook.createSheet(format(time.getStartTime()) + " -- " + formatTimeToC(time.getEndTime()) + "(" + time.getTotal() + "人)" + remarks);
        HSSFCell cell = sheet.createRow(0).createCell(0);
        cell.setCellValue(format(time.getStartTime()) + " -- " + formatTimeToC(time.getEndTime()) + "(" + time.getTotal() + "人)" + remarks);
        Integer id = time.getId();
        int i =1;
        String[] names = timeRecordMapper.queryNotJoin(id);
        for (String name: names) {
            if (name.equals("管理员"))
                continue;
            sheet.createRow(i++).createCell(0).setCellValue(name);
        }
    }

    private String format(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        return sdf.format(date);
    }

    private String formatTimeToC(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH时mm分");
        return sdf.format(date);
    }
    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        return sdf.format(date);
    }

    private String formatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }
}
