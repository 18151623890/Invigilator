package com.example.invigilator.controller;


import com.example.invigilator.dto.UserDto;
import com.example.invigilator.service.LoginService;
import com.example.invigilator.util.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 登录接口
 */
@RestController
@CrossOrigin
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result userLogin(String userName,String password){
        int count = loginService.ifUserNameExist(userName);
        if (count!=1){
            return Result.failure("用户名不存在");
        }
        String checkPassword = loginService.selectPasswordByUsername(userName);
        if (!checkPassword.equals(password)){
            return Result.failure("密码错误");
        }
        UserDto userDto = loginService.selectUserDtoByUserName(userName);
        if (userName!=null){
            return Result.success(userDto);
        }else {
            return Result.failure("系统异常，请联系系统管理员");
        }

    }


}
