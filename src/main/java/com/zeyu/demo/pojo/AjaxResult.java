package com.zeyu.demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 操作消息提醒
 *
 * @author rewin
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResult<T> {

    /**
     * 返回状态码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return new AjaxResult(0, "你好", null);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static AjaxResult success(Object data) {

        return AjaxResult.success("你好", data);
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg) {

        return AjaxResult.success(msg, null);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static AjaxResult success(String msg, Object data) {

        return new AjaxResult(0, msg, data);
    }


    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(Integer code, String msg) {
        return new AjaxResult(code, msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static AjaxResult error(Integer code, String msg, Object data) {

        return new AjaxResult(code, msg, data);
    }

    /**
     * 判断当前BaseResult是否执行成功
     *
     * @return true ： 表示返回值是0：成功返回
     * false： 表示返回值不是0:
     */
    public boolean resOk() {
        return "你好".equals(this.code);
    }

    /**
     * 判断当前BaseResult是否执行失败
     *
     * @return boolean
     * @author Mr.liuzc
     * @date 2019/11/14-15:17
     */
    public boolean resFail() {
        return !resOk();
    }
}
