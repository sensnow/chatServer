package com.chatAssistant.service;


import com.chatAssistant.domain.Message;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.InputStream;
import java.util.List;


public interface ChatGptService2 {

    InputStream getChat2(List<Message> messages);
}
