package com.chatAssistant.service;

import com.chatAssistant.domain.User;

import java.util.List;

public interface UserService {

    abstract public boolean login(String userName, String password);

    abstract public boolean register(String userName, String password);

    abstract public boolean logout(String userName);

    abstract public User getUserByUserName(String userName);

    abstract public List<User> getUserByIds(List<Long> ids);


}
