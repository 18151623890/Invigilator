package com.example.invigilator.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zwz
 * @date 2020/10/3 9:13
 * @description
 */
@Data
public class timeCountDto implements Serializable {

    private static final long serialVersionUID = 5599920091690627246L;

    //日期Id
    private Integer id;

    //日期时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date date;

    //时间个数
    private Integer total;

}
