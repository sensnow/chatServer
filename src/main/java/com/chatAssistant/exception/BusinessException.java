package com.chatAssistant.exception;

import lombok.Data;
import lombok.Getter;

/**
 * 业务异常类
 * @author sensnow
 */
@Getter
public class BusinessException extends RuntimeException{

    /**
     * 代码
     */
    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
