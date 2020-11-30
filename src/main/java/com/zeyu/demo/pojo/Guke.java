package com.zeyu.demo.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "guke")
public class Guke implements Serializable {
    @Id
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "dianhua")
    private String dianhua;
    @Column(name = "mima")
    private String mima;
    @Column(name = "dizhi")
    private String dizhi;
    @Column(name = "yue")
    private Double yue;

}
