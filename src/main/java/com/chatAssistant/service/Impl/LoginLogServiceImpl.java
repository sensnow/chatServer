package com.chatAssistant.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatAssistant.domain.LoginLog;
import com.chatAssistant.service.LoginLogService;
import com.chatAssistant.mapper.LoginLogMapper;
import org.springframework.stereotype.Service;

/**
* @author sensnow
*/
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLog>
    implements LoginLogService{

}




