package com.chatAssistant.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chatAssistant.domain.ChatMsg;
import com.chatAssistant.domain.Message;
import com.chatAssistant.service.ChatGptService;
import com.chatAssistant.service.ChatGptService2;
import com.chatAssistant.service.ConversationLogService;
import com.chatAssistant.utils.JsonParseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EventWebSocketHandler extends TextWebSocketHandler {

    private static ChatGptService2 chatGptService2;

    private static ChatGptService chatGptService;

    private static ConversationLogService conversationLogService;


//    @Autowired
//    public void setChatGptService2(ChatGptService2 chatGptService2){
//        EventWebSocketHandler.chatGptService2 = chatGptService2;
//    }

    @Autowired
    public void setChatGptService(ChatGptService chatGptService){
        EventWebSocketHandler.chatGptService = chatGptService;
    }

    @Autowired
    public void setConversationLogService(ConversationLogService conversationLogService){
        EventWebSocketHandler.conversationLogService = conversationLogService;
    }



    private final Map<String,WebSocketSession> sessions =  new ConcurrentHashMap<>();


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String payload = message.getPayload();
        ChatMsg chatMsg = new ObjectMapper().readValue(payload, ChatMsg.class);
        List<Message> messages = chatMsg.getMessages();
        if(messages.size()>5){
            messages = messages.subList(messages.size()-5,messages.size());
        }
        String searchId = chatMsg.getSearchId();
        if(messages.size()==0){
            return;
        }
        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(chatGptService2.getChat2(messages)));
            BufferedReader in = new BufferedReader(new InputStreamReader(chatGptService.getChatStream(messages)));
            String inputLine = "";
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
                System.out.println(inputLine);
                session.sendMessage(new TextMessage(inputLine));
            }
            in.close();
            session.close();
            conversationLogService.insert(messages.get(messages.size()-1),searchId);
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session.getId());
    }

    public void sendMessage(String clientId, String message) throws IOException {
        WebSocketSession session = sessions.get(clientId);
        if (session != null) {
            session.sendMessage(new TextMessage(message));
        }
    }

}
