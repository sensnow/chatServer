package com.chatAssistant.utils;

import com.chatAssistant.common.Code;
import com.chatAssistant.common.Result;

/**
 * 结果工具类
 * @author sensnow
 */
public class ResultUtils {

        public static <T> Result<T> success(Object object) {
            return new Result<>(Code.SUCCESS,object,"成功");
        }

        public static Result<String> error(Integer code, String msg) {
            return new Result<>(Code.ERROR,null,"失败");
        }
}
