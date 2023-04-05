package com.chatAssistant.service;

import com.chatAssistant.domain.LoginLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
* @author sensnow
* @description 针对表【login_log】的数据库操作Service
* @createDate 2023-04-04 19:35:54
*/
public interface LoginLogService extends IService<LoginLog> {

    /**
     * 插入一条登录日志
     * @param uid 用户id
     * @param date 登录时间
     * @param ip 登录ip
     * @return 插入的行数
     */
    boolean insert(Integer uid, String date, String ip);

}
