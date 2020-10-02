package com.example.invigilator.service.impl;

import com.example.invigilator.entity.User;
import com.example.invigilator.mapper.UserMapper;
import com.example.invigilator.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(User user,Integer roleId,Integer schoolId) {
        int result1 = userMapper.insertUser(user);
        int userId = userMapper.findIdByUserName(user.getUserName());
        int result2 = userMapper.insertUserRole(userId,roleId);
        int result3 = userMapper.insertUserSchool(userId,schoolId);
        if (result1==1&&result2==1&&result3==1){
            return 1;
        }else {
            return 0;
        }
    }

}
