package com.zeyu.demo.exception;

import com.zeyu.demo.pojo.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


import java.util.List;

/**
 * 全局异常处理器
 *
 * @author chenhu
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(NullPointerException.class)
    public AjaxResult handle(NullPointerException e) {
        logPrintWarn(e, "NullPointerException");
        return AjaxResult.error(-100005, "相关参数不能为空");
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public AjaxResult handle(IndexOutOfBoundsException e) {

        logPrintWarn(e, "IndexOutOfBoundsException");
        return AjaxResult.error(-100006, "数组越界");
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public AjaxResult handle(HttpRequestMethodNotSupportedException e) {

        logPrintWarn(e, "HttpRequestMethodNotSupportedException");
        return AjaxResult.error(-100007, "请求方法不匹配");
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public AjaxResult handle(HttpMediaTypeNotSupportedException e) {

        logPrintWarn(e, "HttpMediaTypeNotSupportedException");
        return AjaxResult.error(-100007, "请求资源MediaType不匹配");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult handle(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        logPrintWarn(e, "MethodArgumentNotValidException");
        return AjaxResult.error(-100008, fieldErrors.get(0).getDefaultMessage());
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public AjaxResult handle(NoHandlerFoundException e) {

        logPrintWarn(e, "NoHandlerFoundException");
        return AjaxResult.error(22, "路径不存在，请检查路径是否正确");
    }


    @ExceptionHandler(Exception.class)
    public AjaxResult handleException(Exception e) {
        logPrintError(e, "Exception");
        return AjaxResult.error(-100000, e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return AjaxResult.error(-100010, message);
    }

    /**
     * 打印日志
     *
     * @param e
     * @param ExceptionType
     */
    void logPrintError(Exception e, String ExceptionType) {
        log.error("输出异常堆栈信息", e);
        log.error("统一异常捕获：异常类别：{}， 出现异常,异常信息：{}", ExceptionType, e);
    }

    /**
     * 打印日志
     *
     * @param e
     * @param ExceptionType
     */
    void logPrintWarn(Exception e, String ExceptionType) {
        log.warn("输出异常堆栈信息", e);
        log.warn("统一异常捕获：异常类别：{}， 出现异常,异常信息：{}", ExceptionType, e);
    }
}
