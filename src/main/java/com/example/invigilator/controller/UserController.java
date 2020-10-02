package com.example.invigilator.controller;


import com.example.invigilator.entity.User;
import com.example.invigilator.service.UserService;
import com.example.invigilator.util.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

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
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public Result insertUser(@RequestBody User user){
        //角色id默认1
        int roleId = 1;
        //学校id默认1
        int schoolId =1;
        int result = userService.insertUser(user,roleId,schoolId);
        if (result==1){
            return Result.success("注册成功");
        }else {
            return Result.failure("注册失败");
        }
    }

}
