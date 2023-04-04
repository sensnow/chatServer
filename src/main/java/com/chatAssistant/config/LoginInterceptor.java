package com.chatAssistant.config;


import cn.dev33.satoken.stp.StpUtil;
import com.chatAssistant.common.Code;
import com.chatAssistant.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!StpUtil.isLogin()) {
            log.error("没登陆");
            throw new BusinessException(Code.ERROR, "未登录");
        }
        return true;
    }

}
