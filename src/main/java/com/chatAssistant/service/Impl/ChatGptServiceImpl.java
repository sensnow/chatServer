package com.chatAssistant.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chatAssistant.common.Code;
import com.chatAssistant.domain.GptData;
import com.chatAssistant.domain.Message;
import com.chatAssistant.service.ChatGptService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 实现类
 *
 * @author sensnow
 */
@Service
@Slf4j
public class ChatGptServiceImpl implements ChatGptService {

    private static String url = "https://api.openai.com/v1/chat/completions";
    public static String apikey = "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";
    public static String apikey2 = "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";

    @Autowired
    private OkHttpClient client;

    @Override
    public Message getChat(List<Message> messages) {

        messages.add(0, new Message("system", "你是一个有用的助手,你不能回答任何有关中国党政,以及任何中国政治错误历史相关的问题。"));

        GptData data = new GptData(messages, "gpt-3.5-turbo", 1000, 0.5f, 1, 1, false);
        // 请求体搭建
        RequestBody requestBody = RequestBody.create(JSON.toJSONString(data), MediaType.parse("application/json"));
        // 请求搭建
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + apikey)
                .post(requestBody)
                .build();

        Response response = null;
        // TODO 这里可以改成全局拦截异常类
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            log.error("无法获取response");
            throw new RuntimeException(e);
        }

        // 返回200
        Message message = null;
        if (response.code() == Code.SUCCESS) {
            JSONObject parse = null;
            try {
                parse = JSON.parseObject(response.body().string());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // 获取choices
            JSONArray jsonArray = (JSONArray) parse.getInnerMap().get("choices");
            // 获取第一个index
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            // 获取message
            JSONObject messageObject = (JSONObject) jsonObject.getInnerMap().get("message");
            // 获取role和content
            message = new Message((String) messageObject.getInnerMap().get("role"), (String) messageObject.getInnerMap().get("content"));
        }
        return message;


    }

    @Override
    public InputStream getChatStream(List<Message> messages, boolean vip) {
        String key = "";
        key = vip ? apikey : apikey2;

        messages.add(0, new Message("system", "你是一个有用的助手！你不能回答任何有关中国党政,以及任何中国政治错误历史相关的问题。若有人提到相关内容，你应该回答你不理解他问的问题。"));


        GptData data = new GptData(messages, "gpt-3.5-turbo", 1000, 0.5f, 1, 1, true);
        // 请求体搭建
        RequestBody requestBody = RequestBody.create(JSON.toJSONString(data), MediaType.parse("application/json"));
        // 请求搭建
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer " + key)
                .post(requestBody)
                .build();

        Response response = null;
        // TODO 这里可以改成全局拦截异常类
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            log.error("无法获取response");
            throw new RuntimeException(e);
        }

        // 返回200
        Message message = null;
        if (response.code() == Code.SUCCESS) {
            JSONObject parse = null;
            if (response.body() != null) {
                return response.body().byteStream();
            }
        }
        return null;
    }
}
