package com.example.invigilator.service;

import com.example.invigilator.entity.User;
import org.springframework.stereotype.Service;

public interface UserService {

    int insertUser(User user,Integer roleId,Integer schoolId);


}
