package com.zeyu.demo.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Table(name = "dingdanbiao")
public class Dingdanbiao implements Serializable {
    //
    @Id
    private Integer id;
    //
    @Column(name = "riqi")
    private LocalDateTime riqi;
    //
    @Column(name = "gukeid")
    private Integer gukeid;
}
