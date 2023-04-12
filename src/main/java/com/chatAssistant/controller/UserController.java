package com.chatAssistant.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.chatAssistant.common.Code;
import com.chatAssistant.common.Result;
import com.chatAssistant.domain.User;
import com.chatAssistant.exception.BusinessException;
import com.chatAssistant.request.UserLoginRequest;
import com.chatAssistant.request.UserRegRequest;
import com.chatAssistant.service.Impl.VipServiceImpl;
import com.chatAssistant.service.LoginLogService;
import com.chatAssistant.service.UserService;
import com.chatAssistant.utils.ResultUtils;
import com.chatAssistant.utils.TimeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.chatAssistant.config.UserConfig.reg_available;

/**
 * 用户控制器
 * @author sensnow
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 登录
     * @param user 用户信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginRequest user,HttpServletRequest request) {
        // 参数校验
        if (user.getUserName() == null || user.getPassword() == null) {
            throw new BusinessException(Code.ERROR,"参数错误");
        }
        boolean token = userService.login(user.getUserName(), user.getPassword());
        if (token) {
            // 记录登录信息
            User userByUserName = userService.getUserByUserName(user.getUserName());
            try {
                // 获取IP地址
                String addr = request.getHeader("X-Forwarded-For");
                if(addr == null || addr.length() == 0 || "unknown".equalsIgnoreCase(addr)) {
                    addr = request.getRemoteAddr();
                }
                loginLogService.insert(userByUserName.getUid(), TimeUtils.getTime(), addr);
            } catch (Exception e) {
                System.out.println(e);
            }
            SaSession session = StpUtil.getSession();
            session.set("uid",userByUserName.getUid()); // 在session中设置uid
            return new Result<>(Code.SUCCESS, "/index", "登录成功");
        } else {
            return new Result<>(Code.ERROR,"/login","登录失败");
        }
    }

    /**
     * 注册
     * @param user 用户信息
     * @return 注册结果
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserRegRequest user) {
        if(!reg_available)
            throw new BusinessException(Code.Reg_ERROR,"注册功能已关闭");

        // 参数校验
        if (user.getUserName() == null || user.getPassword() == null|| user.getCheckPassword() == null) {
            throw new BusinessException(Code.ERROR,"参数错误");
        }
        boolean register = userService.register(user.getUserName(), user.getPassword(), user.getCheckPassword(),TimeUtils.getTime());

        if(!register)
        {
           throw new BusinessException(Code.ERROR,"注册失败");
        }
        else
        {
            User userByUserName = userService.getUserByUserName(user.getUserName());
            VipServiceImpl.UserList.put(userByUserName.getUid(),userByUserName);
            return ResultUtils.success("注册成功");
        }

    }

    @PostMapping("/checkUserName")
    public Result<String> checkUserName(@RequestBody User user){
        String userName = user.getUserName();
        boolean b = userService.checkUserName(userName);
        if(b){
            return ResultUtils.error(400,"用户名已存在");
        }else {
            return ResultUtils.success("用户名可用");
        }
    }

    /**
     * 获取当前登录用户信息
     * @return 当前登录用户信息
     */
    @GetMapping("/info")
    public Result<User> info() {
        // 获取当前登录用户信息
        Integer uid = (Integer) StpUtil.getSession().get("uid");
        User userInfo = userService.getUserInfo(uid);
        return ResultUtils.success(userInfo);
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

    @GetMapping("/register")
    public Result<String> register() {
        if (reg_available){
            return ResultUtils.success("可注册");
        }else {
            return ResultUtils.error(Code.Reg_ERROR,"不可注册");
        }
    }

    @PostMapping("/regSwitch/{key}")
    public Result<String> regSwitch(@PathVariable String key) {
        if ("k812vna123dmamp551ss1oytt1".equals(key)){
            reg_available = !reg_available;
            return ResultUtils.success("操作成功");
        }else {
            return ResultUtils.error(Code.ERROR,"操作失败");
        }
    }

}
