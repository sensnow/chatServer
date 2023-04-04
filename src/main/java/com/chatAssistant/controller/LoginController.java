package com.chatAssistant.controller;

import com.chatAssistant.common.Code;
import com.chatAssistant.common.Result;
import com.chatAssistant.domain.User;
import com.chatAssistant.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"/login"})
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    public String Login(@RequestBody User user, HttpServletRequest request) {
        boolean token = userService.login(user.getUserName(), user.getPassword());
        if (token) {
            request.getSession().setAttribute("user", user);
            return "/index";
        } else {
            return "/login";
        }
    }
}
