package com.chatAssistant.exception;


import com.chatAssistant.common.Result;
import com.chatAssistant.utils.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 * @author sensnow
 */
@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(BusinessException.class)
    public Result<String> handleBusinessException(BusinessException e) {
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

}
