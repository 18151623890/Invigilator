package com.example.invigilator.controller;


import com.example.invigilator.dto.DateDto;
import com.example.invigilator.dto.UserDto;
import com.example.invigilator.entity.User;
import com.example.invigilator.service.UserService;
import com.example.invigilator.util.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户接口
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    //用户注册
    @RequestMapping(value = "/insert/{roleId}/{schoolId}",method = RequestMethod.POST)
    public Result insertUser(@RequestBody User user,@PathVariable Integer roleId,@PathVariable Integer schoolId){
        String checkUserName = user.getUserName();
        int count = userService.checkUserName(checkUserName);
        if (count==1){
            return Result.failure("用户名已存在");
        }
        user.setValid(1);
        int result = userService.insertUser(user,roleId,schoolId);
        if (result==1){
            return Result.success("注册成功");
        }else {
            return Result.failure("系统异常，请联系系统管理员");
        }
    }

    //查询所有用户
    @PostMapping(value = "/page/{pageNum}/{pageSize}")
    public Result page(@PathVariable Integer pageNum, @PathVariable Integer pageSize, @RequestBody User user) {
        try {
            Map map = new HashMap();
            Page page = PageHelper.startPage(pageNum, pageSize);
            List<User> users = userService.conditionQuery(user);
            PageInfo pageInfo = new PageInfo(users);

            map.put("total", page.getTotal());
            map.put("list", pageInfo.getList());
            return Result.success(map);
        } catch (Exception e) {
            return Result.failure("系统异常，请联系管理员");
        }
    }

    //删除
    @DeleteMapping(value = "/del/{id}")
    public Result del(@PathVariable Integer id) {
        userService.delUser(id);
        return Result.success("删除成功");
    }

    //查看用户信息
    @GetMapping(value = "/byId/{id}")
    public Result byId(@PathVariable Integer id) {
        try {
            User user = userService.byId(id);
            return Result.success(user);
        } catch (Exception e) {
            return Result.failure("系统异常，请联系管理员");
        }
    }
}
