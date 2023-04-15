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
    boolean register(String userName, String password,String checkPassword,String date);

    /**
     * 登出
     * @return true:登出成功 false:登出失败
     */
    boolean logout();

    /**
     * 根据用户名获取用户
     * @param userName 用户名
     * @return 用户
     */
    User getUserByUserName(String userName);

    /**
     * 根据用户id获取用户
     * @param uid 用户id
     * @return 用户
     */
    User getUserByUid(Integer uid);

    /**
     * 根据用户id获取用户名
     * @param uid 用户id
     * @return 用户名
     */
    String getUserNameByUid(Integer uid);

    /**
     * 检查用户是否存在
     * @param userName
     * @return
     */
    boolean checkUserName(String userName);

    /**
     * 获取用户信息
     * @param uid
     * @return
     */
    User getUserInfo(Integer uid);

    Integer isAvailable(Integer uid);

    Integer updateTotalTokens(Integer uid, Integer totalTokens);

}
