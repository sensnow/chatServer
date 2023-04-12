package com.chatAssistant.config;


import com.chatAssistant.domain.User;
import com.chatAssistant.mapper.UserMapper;
import com.chatAssistant.service.Impl.VipServiceImpl;
import com.chatAssistant.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoadListener implements ApplicationListener<ApplicationReadyEvent> {

    private UserMapper userMapper;


    @Autowired
    public void setProperty(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        List<User> users = userMapper.selectAll();
        for(User user : users){
            Integer uid = user.getUid();
            VipServiceImpl.UserList.put(uid,user);
        }
    }
}
