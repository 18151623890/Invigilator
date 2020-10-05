package com.example.invigilator.service;

import com.example.invigilator.dto.UserDto;
import com.example.invigilator.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    int checkUserName(String checkUserName);

    int insertUser(User user,Integer roleId,Integer schoolId);

    List<User> conditionQuery(User user);

    int delDate(Integer id);

    User byId(Integer id);

    int delUser(Integer id);
}
