package com.chatAssistant.utils;

import com.chatAssistant.common.Result;

/**
 * 结果工具类
 * @author sensnow
 */
public class ResultUtils {

        public static <T> Result<T> success(Object object) {
            return new Result<>(0,object,"成功");
        }

        public static Result<Object> error(Integer code, String msg) {
            return new Result<>(1,null,"失败");
        }
}
