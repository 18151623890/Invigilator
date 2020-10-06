package com.example.invigilator.controller;

import com.example.invigilator.dto.DateDetails;
import com.example.invigilator.dto.DateDto;
import com.example.invigilator.dto.EnlistDto;
import com.example.invigilator.service.InvigilatorService;
import com.example.invigilator.util.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    //分页查询记录-条件日期段
    @PostMapping(value = "/page/{pageNum}/{pageSize}")
    public Result page(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestBody DateDto dto) {
        try {
            Map map = new HashMap();
            Page page = PageHelper.startPage(pageNum, pageSize);
            List<DateDto> dtoList = service.conditionQuery(dto);
            PageInfo pageInfo = new PageInfo(dtoList);

            map.put("total", page.getTotal());
            map.put("list", pageInfo.getList());
            return Result.success(map);
        } catch (Exception e) {
            return Result.failure("系统异常，请联系管理员");
        }
    }

    //停止报名
    @GetMapping(value = "/stop/{id}")
    public Result stop(@PathVariable Integer id){
        try {
            int stop = service.stop(id);
            if (stop > 0){
                return Result.success("已停止报名");
            }else{
                return Result.failure("系统异常，请联系管理员");
            }
        } catch (Exception e) {
            return Result.failure("系统异常，请联系管理员");
        }
    }
    //根据日期ID查询详细信息
    @GetMapping(value = "/byId/{id}")
    public Result byId(@PathVariable Integer id){
        try {
            List<DateDetails> details = service.byId(id);
            return Result.success(details);
        } catch (Exception e) {
            return Result.failure("系统异常，请联系管理员");
        }
    }


    //根据时间ID查询已报名人员

    //添加
    @PostMapping(value = "/add")
    public Result add(@RequestBody DateDto dto) {
        try {
            int i = service.addDate(dto);
            if (i == 0) {
                return Result.failure("至少填写一个时间段");
            } else {
                return Result.success("添加成功");
            }
        } catch (Exception e) {
            return Result.failure("参数不正确，请填写正确的信息");
        }
    }

    //修改

    //删除
    @DeleteMapping(value = "/del/{id}")
    public Result del(@PathVariable Integer id) {
        service.delDate(id);
        return Result.success("删除成功");
    }


    //查询所有时间段
    @PostMapping(value = "/pageTime/{pageNum}/{pageSize}")
    public Result pageTime(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestBody DateDto dto) {
        try {
            Map map = new HashMap();
            Page page = PageHelper.startPage(pageNum, pageSize);
            List<EnlistDto> enlist = service.enlist(dto);
            PageInfo pageInfo = new PageInfo(enlist);

            map.put("total", page.getTotal());
            map.put("list", pageInfo.getList());
            return Result.success(map);
        } catch (Exception e) {
            return Result.failure("系统异常，请联系管理员");
        }
    }

    //报名-->>是否还有名额-->>时间不能重叠
    @GetMapping(value = "/signUp/{uid}/{tid}")
    public Result signUp(@PathVariable Integer uid, @PathVariable Integer tid) {
        try {
            int i = service.signUp(tid, uid);
            if (i == -1) {
                return Result.failure("名额已满，无法继续报名");
            } else if (i == 0) {
                return Result.failure("时间安排冲突，请报名其余时间");
            } else {
                return Result.success("报名成功");
            }
        } catch (Exception e) {
            return Result.failure("系统异常，请联系管理员");
        }

    }

    //取消报名
    @GetMapping(value = "/unSignUp/{uid}/{tid}")
    public Result unSignUp(@PathVariable Integer uid, @PathVariable Integer tid) {
        try {
            int i = service.unSignUp(uid, tid);
            if (i > 0) {
                return Result.success("取消成功");
            }else {
                return Result.failure("系统异常，请联系管理员");
            }
        } catch (Exception e) {
            return Result.failure("系统异常，请联系管理员");
        }
    }


}
