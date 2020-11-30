package com.zeyu.demo.mapper;

import com.zeyu.demo.pojo.Dingdanbiao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface DingdanbiaoMapper  extends TkMapper<Dingdanbiao>  {
    /**
     * 根据id查询；返回单个对象
     */
    Dingdanbiao getDingdanbiaoById(@Param(value = "id") Long id);

    /**
     * 根据条件查询；返回多个对象
     */
    List<Dingdanbiao> getDingdanbiaoListByMap(Map<String, Object> param) throws Exception;

    /**
     * 查询数量：根据传入的条件查询目标数量；返回查询的数量
     */
    Integer getDingdanbiaoCountByMap(Map<String, Object> param) throws Exception;

    /**
     * 添加：根据传入的参数添加信息；返回影响的行数
     */
    Integer insertDingdanbiao(Dingdanbiao dingdanbiao) throws Exception;

    /**
     * 根据id修改：根据传入的参数修改对应的数据库类；返回影响的行数
     */
    Integer updateDingdanbiao(Dingdanbiao dingdanbiao) throws Exception;

    /**
     * 删除： 根据map删除对象；返回影响的行数
     */
    Integer deleteDingdanbiaoByMap(Map<String, Object> param) throws Exception;

}
