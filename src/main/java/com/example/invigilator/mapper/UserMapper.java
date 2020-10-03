package com.example.invigilator.mapper;

import com.example.invigilator.entity.StartTimeAndEndTime;
import com.example.invigilator.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    /************************新增*******************************/
    int checkUserName(String checkUserName);

    int insertUser(User user);

    int findIdByUserName(String userName);

    int insertUserSchool(Integer userId,Integer schoolId);

    int insertUserRole(Integer userId,Integer roleId);
    /**********************************************************/
    //删除
    int del(@Param("id") Integer id);

    //查询用户报名时间
    List<StartTimeAndEndTime> queryTime(Integer uid);

    //报名
    int addTime(@Param("uid") Integer uid,@Param("tid") Integer tid);

}
