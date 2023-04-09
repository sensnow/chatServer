package com.chatAssistant.controller;

import com.chatAssistant.common.Result;
import com.chatAssistant.config.EventWebSocketHandler;
import com.chatAssistant.domain.ChatMsg;
import com.chatAssistant.service.ChatGptService2;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/ws")
public class WsController {

    @Autowired
    private EventWebSocketHandler webSocketHandler;

    @Autowired
    private ChatGptService2 chatGptService2;

    @PostMapping("/connect")
    public Result<String> connect(@RequestBody ChatMsg chatMsg, HttpServletRequest request) throws IOException {
        HttpSession httpSession = request.getSession();
        String sessionId = httpSession.getId();
        httpSession.setAttribute("sessionId", sessionId);
        webSocketHandler.sendMessage(sessionId, "Welcome to WebSocket!");
        return new Result<>(200,sessionId,"ok");
    }

    @PostMapping("/event")
    public void sendEvent(@RequestBody ChatMsg chatMsg,HttpServletRequest request) {
//        String sessionId = chatMsg.getSessionId();
//        String msg = chatMsg.getMsg();
//        String chat = chatGptService2.getChat(msg);
//        try {
//            webSocketHandler.sendMessage(sessionId, chat);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        HttpSession session = request.getSession();
        String sessionId = (String) session.getAttribute("sessionId");
        for(int i = 0;i<=20;i++){
            try {
                Thread.sleep(1000);
                webSocketHandler.sendMessage(sessionId, "Welcome to WebSocket!");
                System.out.println("发送消息");
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
