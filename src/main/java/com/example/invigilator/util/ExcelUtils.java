package com.example.invigilator.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.nio.file.Watchable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zwz
 * @date 2020/10/1 20:44
 * @description Excel工具类
 */
public class ExcelUtils {

    private static HSSFWorkbook workbook = null;

    public static HSSFWorkbook getWorkbook() {
        return workbook;
    }

    public static HSSFSheet createSheet(String sheetName) {
        workbook = new HSSFWorkbook();
        return workbook.createSheet(sheetName);
    }

    public static void setTitleLen(String sheetName, String title, Integer len) {
        HSSFSheet sheet = workbook.getSheet(sheetName);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, len - 1));
        HSSFCell cell = sheet.createRow(0).createCell(0);
        cell.setCellValue(title);
    }

    public static void setRowMerge(String sheetName, Integer row, Integer cell, Integer[] lens) {
        HSSFSheet sheet = workbook.getSheet(sheetName);
        int start = cell - 1;
        int end = 0;
        for (int i = 0; i < lens.length; i++) {
            Integer len = lens[i];
            end = start + len - 1;
            if(start == end){
                start = end + 1;
            }else{
                sheet.addMergedRegion(new CellRangeAddress(row-1, row-1, start, end));
                start = end + 1;
            }
        }

    }



}
