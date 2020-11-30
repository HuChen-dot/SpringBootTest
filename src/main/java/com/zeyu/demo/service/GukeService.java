package com.zeyu.demo.service;

import com.zeyu.demo.pojo.Dipt;
import com.zeyu.demo.pojo.Guke;

import java.util.List;

public interface GukeService {

    //根据id查询用户信息
    Dipt getGukeById(Integer id);
}
