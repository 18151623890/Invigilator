package com.example.invigilator.dto;

import lombok.Data;

@Data
public class UserDto {

    private Integer userId;
    private Integer roleId;
    private String roleName;
    private Integer schoolId;
    private String schoolName;
    private String nickName;

}
