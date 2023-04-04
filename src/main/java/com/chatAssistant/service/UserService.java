package com.chatAssistant.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chatAssistant.domain.User;

public interface UserService extends IService<User> {

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @return true:登录成功 false:登录失败
     */
    boolean login(String userName, String password);

    /**
     * 注册
     * @param userName 用户名
     * @param password 密码
     * @param checkPassword 确认密码
     * @return true:注册成功 false:注册失败
     */
    boolean register(String userName, String password,String checkPassword);

    /**
     * 登出
     * @return true:登出成功 false:登出失败
     */
    boolean logout();



}
