package com.chatAssistant.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.chatAssistant.common.Result;
import com.chatAssistant.domain.ChatMsg;
import com.chatAssistant.domain.Message;
import com.chatAssistant.domain.SearchLog;
import com.chatAssistant.service.*;
import com.chatAssistant.service.Impl.ChatGptServiceImpl;
import com.chatAssistant.utils.ResultUtils;
import com.chatAssistant.utils.TimeUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天处理器
 */
@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatGptService chatService;

    @Autowired
    private ChatGptService2 chatGptService2;

    @Autowired
    private SearchLogService searchLogService;

    @Autowired
    private UserService userService;

    @Autowired
    private ConversationLogService conversationLogService;

    /**
     * 获取聊天内容
     * @param chatMsg 消息列表
     * @return 聊天内容
     */
    @PostMapping("")
    public Result<Message> getChat(@RequestBody ChatMsg chatMsg){
        String searchId = chatMsg.getSearchId();
        List<Message> messages = chatMsg.getMessages();
        System.out.println(searchId);
        try {
            int searchLogCount = searchLogService.getSearchLogCount(searchId);
            if(searchLogCount==0){
                throw new RuntimeException("searchId不存在");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int size = messages.size();
        if(size>0){
            if(size == 1){
                Message message = messages.get(0);
                searchLogService.setDescribe(searchId, message.getContent());
            }

        }else {
            throw new RuntimeException("消息列表为空");
        }
        Message message = messages.get(size - 1);
        conversationLogService.insert(message, searchId);
        Message chat = chatService.getChat(messages);
        conversationLogService.insert(chat,searchId);
        return ResultUtils.success(chat);
    }

    /**
     * 创建新搜索
     * @return 聊天内容
     */
    @GetMapping("")
    public Result<String> getChat(){
        Integer uid = (Integer)StpUtil.getSession().get("uid");
        String userNameByUid = userService.getUserNameByUid(uid);
        String searchId = userNameByUid+System.currentTimeMillis()/1000;
        boolean insert = searchLogService.insert(searchId, uid, TimeUtils.getTime());
        if(insert){
            return ResultUtils.success(searchId);
        }else {
            return ResultUtils.error(400,"创建失败");
        }
    }

    /**
     * 获取聊天记录列表索引
     * @return 聊天内容
     */
    @GetMapping("/conversationlist")
    public Result<List<SearchLog>> getConversation(){
        SaSession session = StpUtil.getSession();
        Integer uid = (Integer) session.get("uid");
        List<SearchLog> allSearchIdByUid = searchLogService.getAllSearchLogByUid(uid);
        return ResultUtils.success(allSearchIdByUid);
    }

    /**
     * 获取聊天内容
     * @return 聊天内容
     */
    @GetMapping("/conversation")
    public Result<List<Message>> getMessage(HttpServletRequest request){
        String searchId = request.getParameter("searchId");
        List<Message> allMessageBySearchId = conversationLogService.getMessagesBySearchId(searchId);
        return ResultUtils.success(allMessageBySearchId);
    }


    /**
     * 删除聊天记录
     * @return 聊天内容
     */
    @DeleteMapping("/conversation")
    public Result<String> deleteMessage(HttpServletRequest request){
        String searchId = request.getParameter("searchId");
        System.out.println(searchId);
        Integer uid = searchLogService.getBySearchId(searchId).getUid();
        if(!uid.equals(StpUtil.getSession().get("uid"))){
            return ResultUtils.error(400,"无权限");
        }
        int i = searchLogService.deleteBySearchIdInt(searchId);
        if(i >0){
            return ResultUtils.success("删除成功");
        }else {
            return ResultUtils.error(400,"删除失败");
        }
    }

    @DeleteMapping("/allconversation")
    public Result<String> deleteAllMessage(HttpServletRequest request){
        Integer uid = (Integer)StpUtil.getSession().get("uid");
        int i = searchLogService.deleteByUid(uid);
        if(i >0){
            return ResultUtils.success("删除成功");
        }else {
            return ResultUtils.error(400,"删除失败");
        }
    }


    @PutMapping("/updatekey/{key}")
    public Result<String> updateKey(@PathVariable String key){
        ChatGptServiceImpl.apikey = key;
        return ResultUtils.success("更新成功");
    }

    @PostMapping("/saveConversation")
    public Result<String > saveConversation(@RequestBody ChatMsg chatMsg){
        String searchId = chatMsg.getSearchId();
        List<Message> messages = chatMsg.getMessages();
        System.out.println(searchId);
        try {
            int searchLogCount = searchLogService.getSearchLogCount(searchId);
            if(searchLogCount==0){
                throw new RuntimeException("searchId不存在");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int size = messages.size();
        if(size == 1){
            Message message = messages.get(0);
            conversationLogService.insert(message, searchId);
        }else {
            throw new RuntimeException("插入失败");
        }
        return new Result<>(200,null,"OK");
    }

}
