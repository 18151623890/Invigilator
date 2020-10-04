package com.example.invigilator.service;

import com.example.invigilator.dto.UserDto;
import org.springframework.stereotype.Service;

public interface LoginService {
    //判断用户名是否存在
    int ifUserNameExist(String userName);
    //根据用户名查找密码，在controller层判断是否正确
    String selectPasswordByUsername(String userName);
    //登录成功返回UserDto
    UserDto selectUserDtoByUserName(String userName);

}
