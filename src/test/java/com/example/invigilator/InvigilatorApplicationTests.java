package com.example.invigilator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class InvigilatorApplicationTests {

    @Test
    void contextLoads0() throws IOException {
        /*Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        Student student = new Student(1, "张三", format);
        String[] titles = {"编号", "名字", "创建日期"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学生信息");
        HSSFRow row = sheet.createRow(0);

        for (int i = 0; i < titles.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(titles[i]);
        }

        HSSFRow rows = sheet.createRow(1);

        rows.createCell(0).setCellValue(student.getId());
        rows.createCell(1).setCellValue(student.getName());
        rows.createCell(2).setCellValue(student.getCreateTime());

        File file = new File("D://test.xls");

        workbook.write(new FileOutputStream(file));*/

    }

    @Test
    void contextLoads1() throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("监考报名单");

        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11 - 1));
        HSSFCell cell = sheet.createRow(0).createCell(0);
        cell.setCellValue("标题");

        Integer row = 2;
        Integer cells = 2;
        Integer[] lens = new Integer[]{2, 3, 1, 4};
        List<Integer> startDate = new ArrayList<>();
        int start = cells - 1;
        int end = 0;
        for (int i = 0; i < lens.length; i++) {
            Integer len = lens[i];
            end = start + len - 1;
            if (start == end) {
                start = end + 1;
                startDate.add(start);
            } else {
                sheet.addMergedRegion(new CellRangeAddress(row - 1, row - 1, start, end));
                startDate.add(start);
                start = end + 1;
            }

        }
        sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));

        HSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("姓名");
        for (Integer i: startDate){
            row1.createCell(i).setCellValue(i);
        }

        HSSFRow row2 = sheet.createRow(2);
        row2.createCell(1).setCellValue(1);

        File file = new File("C:\\Users\\zwz\\Desktop\\test.xls");
        workbook.write(new FileOutputStream(file));

    }

    @Test
    void contextLoads2(){

    }

}
