package com.chatAssistant.service;

import com.chatAssistant.domain.ConversationLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chatAssistant.domain.Message;

import java.util.List;

/**
* @author sensnow
* @description 针对表【conservation_log】的数据库操作Service
* @createDate 2023-04-04 19:35:54
*/
public interface ConversationLogService extends IService<ConversationLog> {

    boolean insert(Message message, String searchId);

    List<Message> getMessagesBySearchId(String searchId);


    boolean deleteLastMsg(String searchId);
}
