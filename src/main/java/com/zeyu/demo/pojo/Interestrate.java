package com.zeyu.demo.pojo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Interestrate implements Serializable {
    //主键
    private Integer sysId;
    //融资利率
    private Double sysRate;
    //融卷利率
    private Double sysRating;
    //融资逾期罚息率
    private Double sysRateOverdue;
    //融卷逾期罚息率
    private Double sysRatingOverdue;
    //修改时间
    private Date modificationTime;

}
