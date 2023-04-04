package com.chatAssistant.service.Impl;

import com.chatAssistant.domain.User;
import com.chatAssistant.mapper.UserMapper;
import com.chatAssistant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) {
        User login = userMapper.login(userName, password);
        return login != null;
    }

    @Override
    public boolean register(String userName, String password) {
        User user = userMapper.getUserByUserName(userName);
        if(user == null) {
            userMapper.insertUser(userName,password);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean logout(String userName) {
        return false;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public List<User> getUserByIds(List<Long> ids) {
        return userMapper.getUserByIds(ids);
    }
}
