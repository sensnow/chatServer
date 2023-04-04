package com.chatAssistant.request;

import lombok.Data;

/**
 * 用户注册请求类
 * @author sensnow
 */
@Data
public class UserRegRequest {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 确认密码
     */
    private String checkPassword;
}
