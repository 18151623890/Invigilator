package com.example.invigilator.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @author zwz
 * @date 2020/10/3 21:46
 * @description
 */
public interface ExcelService {

    //导出
    HSSFWorkbook export(List<Integer> dateId);

}
