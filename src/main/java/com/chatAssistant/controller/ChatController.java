package com.chatAssistant.controller;

import com.chatAssistant.common.Result;
import com.chatAssistant.domain.Message;
import com.chatAssistant.domain.User;
import com.chatAssistant.service.ChatGptService;
import com.chatAssistant.service.SearchLogService;
import com.chatAssistant.service.UserService;
import com.chatAssistant.utils.ResultUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 聊天处理器
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatGptService chatService;

    @Autowired
    private SearchLogService searchLogService;

    @Autowired
    private UserService userService;

    /**
     * 获取聊天内容
     * @param messages 消息列表
     * @return 聊天内容
     */
    @PostMapping("")
    public Result<Message> getChat(@RequestBody List<Message> messages){
        Message chat = chatService.getChat(messages);
        return ResultUtils.success(chat);
    }

    /**
     * 创建新搜索
     * @param request 请求
     * @return 聊天内容
     */
    @GetMapping("")
    public Result<String> getChat(HttpServletRequest request){
        Integer uid = Integer.valueOf(request.getParameterValues("uid")[0]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = java.time.LocalDateTime.now().format(formatter);
        String userNameByUid = userService.getUserNameByUid(uid);
        String searchId = userNameByUid+System.currentTimeMillis();
        boolean insert = searchLogService.insert(searchId, uid, time);
        if(insert){
            return ResultUtils.success(searchId);
        }else {
            return ResultUtils.error(400,"创建失败");
        }
    }

}
