package com.chatAssistant.utils;

import com.chatAssistant.domain.Message;

import java.util.List;

public class MessageReduceUtils {
    public static void reduce(List<Message> messages){
        for (Message message : messages) {
            if (message.getRole().equals("assistant")
                    && message.getContent().length() > 15) {
                message.setContent(message.getContent().substring(0, 15) + "...");
            }
        }
//        return messages;
    }
}
