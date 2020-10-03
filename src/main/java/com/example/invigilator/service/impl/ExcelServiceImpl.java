package com.example.invigilator.service.impl;

import com.example.invigilator.entity.DateTotal;
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
import java.util.List;
import java.util.Map;

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
        Map<String, Integer> timeMap = new HashMap();
        HSSFRow row2 = sheet.createRow(2);
        int i = 1;
        for (StartTimeAndEndTime time : timeLists) {
            timeMap.put(time.getId().toString(), i);
            row2.createCell(i++).setCellValue(formatTime(time.getStartTime()) + " - " + formatTime(time.getEndTime()));
        }
        List<UserHaveTime> users = userMapper.exportUser(dateId);
        int rows = 3;
        for (UserHaveTime userHaveTime : users){
            HSSFRow row3 = sheet.createRow(rows);
            row3.createCell(0).setCellValue(userHaveTime.getNickname());
            String tid = userHaveTime.getTid();
            String[] split = tid.split(",");
            for (int j=0;j<split.length;j++){
                String s = split[j];
                Integer cel = timeMap.get(s);
                row3.createCell(cel).setCellValue("√");
            }
            rows++;
        }

        return workbook;
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    private String formatTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(date);
    }
}
