package com.chatAssistant.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.chatAssistant.domain.ConservationLog;
import com.chatAssistant.domain.GptData;
import com.chatAssistant.domain.Message;

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
}
