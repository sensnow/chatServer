package com.chatAssistant.config;

import com.chatAssistant.service.ChatGptService2;
import com.chatAssistant.service.Impl.ChatGptServiceImpl2;
import okhttp3.OkHttpClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * bean配置类
 * @author sensnow
 */
@Configuration
public class BeanConfig {


    @Bean
    public OkHttpClient getOkHttpClient(){
        // 代理
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1",  7890));
        // 客户端
        return new OkHttpClient.Builder()
//                .proxy(proxy)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }
}
