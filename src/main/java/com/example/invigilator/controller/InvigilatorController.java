package com.example.invigilator.controller;

import com.example.invigilator.dto.DateDto;
import com.example.invigilator.service.InvigilatorService;
import com.example.invigilator.util.Result;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zwz
 * @date 2020/10/2 0:24
 * @description 监考管理接口
 */
@RestController
@RequestMapping(value = "/invigilator")
public class InvigilatorController {

    @Resource
    private InvigilatorService service;

    //分页查询记录-条件时间段，上午/下午
    @PostMapping(value = "page/{pageNum}/{pageSize}")
    public Result page(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestBody DateDto dto) {
        PageHelper.startPage(pageNum, pageSize);
        List<DateDto> dtoList = service.conditionQuery(dto);
        PageInfo pageInfo = new PageInfo(dtoList);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("list", pageInfo.getList());
        return Result.success(map);
    }

    //根据日期ID查询详细信息

    //根据时间ID查询已报名人员

    //添加

    //修改

    //删除

}
