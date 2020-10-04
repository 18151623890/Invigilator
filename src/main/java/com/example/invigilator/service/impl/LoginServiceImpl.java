package com.example.invigilator.service.impl;

import com.example.invigilator.dto.UserDto;
import com.example.invigilator.mapper.LoginMapper;
import com.example.invigilator.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginMapper loginMapper;
    /*************************登录****************************/
    @Override
    public int ifUserNameExist(String userName) {
        return loginMapper.ifUserNameExist(userName);
    }

    @Override
    public String selectPasswordByUsername(String userName) {
        return loginMapper.selectPasswordByUsername(userName);
    }

    @Override
    public UserDto selectUserDtoByUserName(String userName) {
        return loginMapper.selectUserDtoByUserName(userName);
    }
    /*******************************************************/
}
