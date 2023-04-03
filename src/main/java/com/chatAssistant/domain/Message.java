package com.chatAssistant.domain;


import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 消息类
 * @author sensnow
 */
@Data
@AllArgsConstructor
public class Message {
    private String role;
    private String content;

}
