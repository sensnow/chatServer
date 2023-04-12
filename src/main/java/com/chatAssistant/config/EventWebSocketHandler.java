package com.chatAssistant.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chatAssistant.domain.ChatMsg;
import com.chatAssistant.domain.Message;
import com.chatAssistant.domain.SearchLog;
import com.chatAssistant.service.*;
import com.chatAssistant.utils.JsonParseUtils;
import com.chatAssistant.utils.MessageReduceUtils;
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


    private static ChatGptService chatGptService;

    private static ConversationLogService conversationLogService;

    private static SearchLogService searchLogService;

    private static VipService vipService;


    @Autowired
    public void setAttribute(ConversationLogService conversationLogService,
                             SearchLogService searchLogService,
                             ChatGptService chatGptService,
                             VipService vipService){
        EventWebSocketHandler.conversationLogService = conversationLogService;
        EventWebSocketHandler.searchLogService = searchLogService;
        EventWebSocketHandler.chatGptService = chatGptService;
        EventWebSocketHandler.vipService = vipService;
    }



    private final Map<String,WebSocketSession> sessions =  new ConcurrentHashMap<>();


    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        String payload = message.getPayload();
        ChatMsg chatMsg = new ObjectMapper().readValue(payload, ChatMsg.class);
        List<Message> messages = chatMsg.getMessages();
        String role = messages.get(messages.size() - 1).getRole();
        MessageReduceUtils.reduce(messages);
        if(role.equals("assistant")){
            boolean b = conversationLogService.deleteLastMsg(chatMsg.getSearchId());
            if(!b){
                throw new RuntimeException("searchId不存在");
            }
            messages = messages.subList(0,messages.size()-1);
        }
        if(messages.size()>5){
            messages = messages.subList(messages.size()-5,messages.size());
        }
        String searchId = chatMsg.getSearchId();
        if(messages.size()==0){
            return;
        }else if(messages.size()==1){
            String content = messages.get(0).getContent();
            if(content.length()>10){
                content = content.substring(0,10);
            }
            int i = searchLogService.setDescribe(searchId, content);
            if(i==0){
                throw new RuntimeException("searchId不存在");
            }
        }
        try {
            boolean vip = vipService.isVip(searchId);
            BufferedReader in = new BufferedReader(new InputStreamReader(chatGptService.getChatStream(messages,vip)));
            String inputLine = "";
            while ((inputLine = in.readLine()) != null) {
                try {
                    session.sendMessage(new TextMessage(inputLine));
                } catch (IOException e) {
                    in.close();
                }
            }
            in.close();
//            session.close();
            if(!role.equals("assistant")){
                conversationLogService.insert(messages.get(messages.size()-1),searchId);
            }
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
