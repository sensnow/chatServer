package com.chatAssistant.common;

/**
 * 状态码
 * @author sensnow
 */
public interface Code {
    /**
     * 成功
     */
    Integer SUCCESS = 200;
    /**
     * 失败
     */
    Integer ERROR = 500;

    /**
     * 禁止注册
     */
    Integer Reg_ERROR = 501;

    /**
     * 用户名已存在
     */
    Integer USER_NAME_EXIST = 502;


}
