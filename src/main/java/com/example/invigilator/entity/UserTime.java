package com.example.invigilator.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zwz
 * @date 2020/10/2 0:14
 * @description 用户时间关联表
 */
@Data
public class UserTime implements Serializable {

    private static final long serialVersionUID = -4936874088812633284L;

    //主键
    private Integer id;

    //用户主键
    private Integer uid;

    //时间主键
    private Integer tid;

}
