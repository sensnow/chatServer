package com.chatAssistant.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chatAssistant.common.Code;
import com.chatAssistant.domain.GptData;
import com.chatAssistant.domain.Message;
import com.chatAssistant.service.ChatGptService2;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

/**
 * 实现类
 * @author sensnow
 */
@Slf4j
@Service
public class ChatGptServiceImpl2 implements ChatGptService2 {

    private static String api = "https://api.openai.com/v1/chat/completions";
    private static String apikey = "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";


    @Override
    public InputStream getChat2(List<Message> messages) {
        try {
            GptData data = new GptData(messages,"gpt-3.5-turbo",1000,0.5f,1,1,true);
            URL url = new URL(api);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 7890)));
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("Authorization", "Bearer " + apikey);
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            os.write(JSON.toJSONString(data).getBytes());
            os.close();
            int responseCode = httpURLConnection.getResponseCode();
            String responseMessage = httpURLConnection.getResponseMessage();
            System.out.println("responseCode: " + responseCode);
            System.out.println("responseMessage: " + responseMessage);
            SseEmitter emitter = new SseEmitter();
            if (responseCode == HttpURLConnection.HTTP_OK) {
//                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                return httpURLConnection.getInputStream();
            } else {
                throw new IOException("Unexpected response code: " + responseCode);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }
}
