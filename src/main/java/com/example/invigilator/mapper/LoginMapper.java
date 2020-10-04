package com.example.invigilator.mapper;

import com.example.invigilator.dto.UserDto;

public interface LoginMapper {

    //判断用户名是否存在
    int ifUserNameExist(String userName);
    //判断密码是否正确
    String selectPasswordByUsername(String userName);
    //根据userName查找该user登录成功后返回前端的dto
    UserDto selectUserDtoByUserName(String userName);

}
