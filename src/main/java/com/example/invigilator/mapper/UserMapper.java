package com.example.invigilator.mapper;

import com.example.invigilator.entity.User;

public interface UserMapper {
    int insertUser(User user);

    int findIdByUserName(String userName);

    int insertUserSchool(Integer userId,Integer schoolId);

    int insertUserRole(Integer userId,Integer roleId);

}
