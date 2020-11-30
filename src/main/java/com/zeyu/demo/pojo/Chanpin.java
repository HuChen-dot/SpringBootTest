package com.zeyu.demo.pojo;

import com.zeyu.demo.test.TestAnnotation;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TestAnnotation(content = "你好")
public class Chanpin implements Serializable {

    //
    @TestAnnotation(content = "陈虎")
    private String name;
    //
    private Integer id;
    //
    private Integer gongyinid;
    //
    private Double jiage;
    //
    private String miaoshu;

    public Chanpin() {
    }

}
