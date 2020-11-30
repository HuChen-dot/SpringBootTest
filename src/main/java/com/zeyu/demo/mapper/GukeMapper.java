package com.zeyu.demo.mapper;

import com.zeyu.demo.pojo.Dingdanbiao;
import com.zeyu.demo.pojo.Guke;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface GukeMapper extends TkMapper<Guke>, DaoMapper<Guke> {

    int cal(Map<String, Object> param);


    int insertGukeList(List<Guke> list);

}
