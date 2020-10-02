package com.example.invigilator.entity;

import lombok.Data;

@Data
public class School {
    //主键
    private Integer schoolId;
    //学校名称
    private String schoolName;
    //有效性（0.无效 1.有效）
    private Integer valid;
    //备注
    private String remark;

}
