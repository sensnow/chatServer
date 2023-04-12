package com.chatAssistant.service;


import com.chatAssistant.domain.Message;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.InputStream;
import java.util.List;

/**
 * gpt接口类
 * @author sensnow
 */
public interface ChatGptService {
    /**
     * 获取聊天
     * @param messages 聊天信息
     * @return 聊天
     */
    Message getChat(List<Message> messages);


    InputStream getChatStream(List<Message> messages,boolean vip);



}
