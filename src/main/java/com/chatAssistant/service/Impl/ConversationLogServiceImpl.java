package com.chatAssistant.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatAssistant.domain.ConversationLog;
import com.chatAssistant.domain.Message;
import com.chatAssistant.service.ConversationLogService;
import com.chatAssistant.mapper.ConversationLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sensnow
 */
@Service
public class ConversationLogServiceImpl extends ServiceImpl<ConversationLogMapper, ConversationLog>
        implements ConversationLogService {

    @Autowired
    private ConversationLogMapper conversationLogMapper;

    @Override
    public boolean insert(Message message, String searchId) {
        String content = message.getContent();
        String role = message.getRole();
        ConversationLog conversationLog = new ConversationLog(null, searchId, content, role);
        return conversationLogMapper.insert(conversationLog) == 1;
    }

    @Override
    public List<Message> getMessagesBySearchId(String searchId) {
        return conversationLogMapper.getMessagesBySearchId(searchId);
    }

    @Override
    public boolean deleteLastMsg(String searchId) {
        return conversationLogMapper.deleteLastMsg(searchId) == 1;
    }
}




