package com.example.invigilator.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zwz
 * @date 2020/10/5 7:19
 * @description
 */
@Data
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 1001548765909005844L;

    private String username;

    private String password;

}
