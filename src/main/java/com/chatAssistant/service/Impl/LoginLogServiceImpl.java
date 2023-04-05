package com.chatAssistant.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatAssistant.domain.LoginLog;
import com.chatAssistant.service.LoginLogService;
import com.chatAssistant.mapper.LoginLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author sensnow
*/
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog> implements LoginLogService{

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public boolean insert(Integer uid, String date, String ip) {
        return loginLogMapper.insert(new LoginLog(0, date, uid, ip)) == 1;
    }
}




