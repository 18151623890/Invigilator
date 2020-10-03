package com.example.invigilator.controller;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zwz
 * @date 2020/10/1 20:44
 * @description Excel导入导出
 */
@Controller
public class ExcelController {

    @GetMapping(value = "/excelDownload")
    public void excelDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Integer> ids = new ArrayList<>();
        Iterator it = request.getParameterMap().keySet().iterator();
        while (it.hasNext()){
            ids.add(Integer.parseInt(request.getParameter((String) it.next())));
        }
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;");
        response.flushBuffer();
        workbook.write(response.getOutputStream());

    }

}
