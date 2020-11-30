package com.zeyu.demo.pojo;

import com.zeyu.demo.util.excle.in.Excels;
import lombok.Data;

/**
 * @program: SpringBootTest
 * @description: 测试模板
 * @author: chenhu
 * @create: 2020-11-24 19:37
 **/
@Data
public class Ce {
    @Excels(name = "姓名", type = Excels.Type.IMPORT)
    String name;
    @Excels(name = "年龄", type = Excels.Type.IMPORT)
    String age;
    @Excels(name = "地址", type = Excels.Type.IMPORT)
    String order;
}
