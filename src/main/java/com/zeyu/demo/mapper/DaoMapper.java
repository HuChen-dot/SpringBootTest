package com.zeyu.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DaoMapper<T> {
    /**
     * 根据id查询；返回单个对象
     */
    T getGukeById(@Param(value = "id") Long id);

    /**
     * 根据条件查询；返回多个对象
     */
    List<T> getGukeListByMap(Map<String, Object> param);

    /**
     * 查询数量：根据传入的条件查询目标数量；返回查询的数量
     */
    Integer getGukeCountByMap(Map<String, Object> param);

    /**
     * 添加：根据传入的参数添加信息；返回影响的行数
     */
    Integer insertGuke(T T) throws Exception;

    /**
     * 根据id修改：根据传入的参数修改对应的数据库类；返回影响的行数
     */
    Integer updateGuke(T T) throws Exception;

    /**
     * 删除： 根据map删除对象；返回影响的行数
     */
    Integer deleteGukeByMap(Map<String, Object> param) throws Exception;
}
