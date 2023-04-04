package com.chatAssistant.controller;

import com.chatAssistant.common.Code;
import com.chatAssistant.common.Result;
import com.chatAssistant.domain.User;
import com.chatAssistant.exception.BusinessException;
import com.chatAssistant.request.UserLoginRequest;
import com.chatAssistant.request.UserRegRequest;
import com.chatAssistant.service.UserService;
import com.chatAssistant.utils.ResultUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户控制器
 * @author sensnow
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param user 用户信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginRequest user) {
        // 参数校验
        if (user.getUserName() == null || user.getPassword() == null) {
            throw new BusinessException(Code.ERROR,"参数错误");
        }
        boolean token = userService.login(user.getUserName(), user.getPassword());
        if (token) {
            return new Result<String>(Code.SUCCESS,"/index","登录成功");
        } else {
            return new Result<String>(Code.ERROR,"/login","登录失败");
        }
    }

    /**
     * 注册
     * @param user 用户信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserRegRequest user) {

        // 参数校验
        if (user.getUserName() == null || user.getPassword() == null|| user.getCheckPassword() == null) {
            throw new BusinessException(Code.ERROR,"参数错误");
        }
        boolean register = userService.register(user.getUserName(), user.getPassword(), user.getCheckPassword());
        if(!register)
        {
           throw new BusinessException(Code.ERROR,"注册失败");
        }
        else
        {
            return ResultUtils.success("注册成功");
        }

    }

    /**
     * 登出
     * @return 登出结果
     */
    @GetMapping("/logout")
    public Result<String> logout() {
        // 登出
        userService.logout();
        return ResultUtils.success("登出成功");
    }
}
