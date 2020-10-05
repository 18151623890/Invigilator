package com.example.invigilator.controller;


import com.example.invigilator.dto.LoginDto;
import com.example.invigilator.dto.UserDto;
import com.example.invigilator.service.LoginService;
import com.example.invigilator.util.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Result userLogin(@RequestBody LoginDto dto){
        int count = loginService.ifUserNameExist(dto.getUsername());
        if (count!=1){
            return Result.failure("用户名不存在或密码错误");
        }
        String checkPassword = loginService.selectPasswordByUsername(dto.getUsername());
        if (!checkPassword.equals(dto.getPassword())){
            return Result.failure("用户名不存在或密码错误");
        }
        UserDto userDto = loginService.selectUserDtoByUserName(dto.getUsername());
        if (userDto!=null){
            return Result.success(userDto);
        }else {
            return Result.failure("用户名不存在或密码错误");
        }

    }


}
