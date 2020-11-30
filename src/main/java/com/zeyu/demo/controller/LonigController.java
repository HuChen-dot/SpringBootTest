package com.zeyu.demo.controller;

import com.zeyu.demo.mapper.DingdanbiaoMapper;
import com.zeyu.demo.mapper.GukeMapper;
import com.zeyu.demo.pojo.*;
import com.zeyu.demo.service.GukeService;
import com.zeyu.demo.util.excle.in.ExcelUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LonigController {
    @Autowired
    GukeMapper gukeMapper;
    @Autowired
    GukeService GukeServiceImpl;
    @Autowired
    DingdanbiaoMapper dingdanbiaoMapper;


    @PostMapping("get")
    public String getGuke(@RequestBody @Valid User user) {
        System.err.println("你好" + user);
        return "你好";

//        Dingdanbiao dingdanbiaoById = dingdanbiaoMapper.getDingdanbiaoById(1L);
//        System.err.println(dingdanbiaoById);
//        System.err.println(dingdanbiaoById.getRiqi().format(DateTimeFormatter.ofPattern("yyyy年MM月hh日")));
//        SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = "2019-12-11 19:13:20";
//        try {
//            Date parse = dd.parse(time);
//            Map<String, Object> map = new ConcurrentHashMap<>(4);
//            map.put("dianhua", 666);
//            map.put("id", 5);
//            map.put("times", time);
//            gukeMapper.cal(map);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Guke gukeById = gukeMapper.getGukeById(1l);
//        System.err.println(gukeById);
//        List<Guke> gukes = gukeMapper.selectAll();
//        for (Guke guke : gukes) {
//            System.err.println(guke);
//        }
        //调用分页插件传入开始页码和页面容量
//        Page<Object> page = PageHelper.startPage(8, 10);
////        查询数据库
//        Map<String, Object> map = new ConcurrentHashMap<>(0);
//        List<Guke> gukeListByMap = gukeMapper.getGukeListByMap(map);
//        //输出信息
//        System.out.println("当前页码：" + page.getPageNum());
//        System.out.println("每页的记录数：" + page.getPageSize());
//        System.out.println("总记录数：" + page.getTotal());
//        System.out.println("总页码：" + page.getPages());
//
//        // 把分页出的数据封装进PageInfo对象中
//        PageInfo<Guke> info = new PageInfo<Guke>(gukeListByMap);
//        System.out.println("当前页码：" + info.getPageNum());
//        System.out.println("每页的记录数：" + info.getPageSize());
//        System.err.println(gukeListByMap.size());
    }

    @RequestMapping("gte2")
    public AjaxResult getGuke2() {
        System.err.println("你号2");
        Dipt gukeById = GukeServiceImpl.getGukeById(1);


        return AjaxResult.success(gukeById);
    }

    @RequestMapping("gte3")
    public AjaxResult getGuke3() throws Exception {
        System.err.println("你号3");
        List<Guke> list = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            Guke guke = new Guke();
            guke.setDianhua("dsighv");
            guke.setDizhi("dduigidfu0");
            guke.setMima("123456");
            guke.setName("你们宁");
            guke.setYue(1312.0);
//            guke.setId(i + 101);
            list.add(guke);
        }
        int i = gukeMapper.insertGukeList(list);
        for (Guke guke1 : list) {
            //获取自增主键
            System.err.println(guke1.getId());
        }

//        Integer integer = gukeMapper.insertGuke(guke);
//        System.err.println(guke.getId());

        return AjaxResult.success(i);
    }

    @RequestMapping("get4")
    public AjaxResult get4(MultipartFile file) throws IOException, IllegalAccessException, InvalidFormatException, InstantiationException {
        ExcelUtils<Ce> util = new ExcelUtils(Ce.class);
        List<Ce> ces = util.importExcel(file.getInputStream());
        System.err.println("list大小：" + ces.size());
        for (Ce ce : ces) {
            System.err.println(ce);
        }
        return AjaxResult.success(1);
    }
}
