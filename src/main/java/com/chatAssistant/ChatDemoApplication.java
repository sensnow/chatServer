package com.chatAssistant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan("com.chatAssistant.mapper")
@EnableWebMvc
public class ChatDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatDemoApplication.class, args);
	}

}
