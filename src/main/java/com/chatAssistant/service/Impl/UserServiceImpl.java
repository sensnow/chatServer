package com.chatAssistant.service.Impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatAssistant.common.Code;
import com.chatAssistant.domain.User;
import com.chatAssistant.exception.BusinessException;
import com.chatAssistant.mapper.UserMapper;
import com.chatAssistant.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) {
        // 不能为空
        if("".equals(userName.trim()) || "".equals(password.trim())) {
            throw new BusinessException(Code.ERROR, "用户名或密码不能为空");
        }
        // 密码加密
        String encode = DigestUtils.md5DigestAsHex(password.getBytes());
//        User login = userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName).eq("password", encode));
        User login = userMapper.login(userName, encode);
        // 登录
        if(login != null) {
            StpUtil.login(login.getUid());
        }
        return login != null;
    }

    @Override
    public boolean register(String userName, String password,String checkPassword,String date) {

        // 密码不同
        if(!password.equals(checkPassword)) {
            throw new BusinessException(Code.ERROR, "两次密码不同");
        }
        // 存在相同用户名
        if(userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName)) != null) {
            throw new BusinessException(Code.ERROR, "用户名已存在");
        }
        // 密码加密
        String encode = DigestUtils.md5DigestAsHex(password.getBytes());
        User user = new User(0,userName, encode, date,4);
        int insert = userMapper.insert(user);
        return insert == 1;
    }

    @Override
    public boolean logout() {
        // 登出
        StpUtil.logout();
        return true;
    }

    @Override
    public User getUserByUserName(String userName) {
//        return userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userName));
        return userMapper.getUserByUserName(userName);
    }

    @Override
    public User getUserByUid(Integer uid) {
        return userMapper.getUserByUid(uid);
    }

    @Override
    public String getUserNameByUid(Integer uid) {
        return userMapper.getUserNameByUid(uid);
    }

    @Override
    public boolean checkUserName(String userName) {
        if("".equals(userName.trim())) {
            throw new BusinessException(Code.ERROR, "用户名不能为空");
        }
        return userMapper.checkUserName(userName) == 1;
    }

    @Override
    public User getUserInfo(Integer uid) {
        return userMapper.getUserByUid(uid);
    }

    @Override
    public Integer isAvailable(Integer uid) {
        return userMapper.getIsAvailable(uid);
    }

    @Override
    public Integer updateTotalTokens(Integer uid, Integer totalTokens) {
        return userMapper.updateTotalTokens(uid, totalTokens);
    }


}
