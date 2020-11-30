package com.zeyu.demo.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Future;

import java.util.Date;


@Data
public class User {

    private Integer id;

    @Future(message = "必须是一个将来的日期")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    @NotBlank(message = "姓名不能为空")
    private String name;
}
