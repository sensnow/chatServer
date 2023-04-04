package com.chatAssistant.request;

import lombok.Data;

/**
 * 用户登录请求类
 * @author sensnow
 */
@Data
public class UserLoginRequest {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
}
