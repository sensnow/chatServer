package com.chatAssistant.controller;

import com.chatAssistant.common.Result;
import com.chatAssistant.domain.User;
import com.chatAssistant.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public Result<String> register(@RequestBody User user, HttpServletRequest request) {
        User user1 = userService.getUserByUserName(user.getUserName());
        if(user1 == null) {
            userService.register(user.getUserName(), user.getPassword());
            return new Result<>(200, "/login", "注册成功");
        } else {
            return new Result<>(400, "/register", "注册失败");
        }
    }
}
