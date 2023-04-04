package com.chatAssistant.controller;

import com.chatAssistant.common.Result;
import com.chatAssistant.domain.Message;
import com.chatAssistant.service.ChatGptService;
import com.chatAssistant.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 聊天处理器
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatGptService chatService;

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

}
