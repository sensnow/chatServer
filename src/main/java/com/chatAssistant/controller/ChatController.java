package com.chatAssistant.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.chatAssistant.common.Result;
import com.chatAssistant.domain.ConversationLog;
import com.chatAssistant.domain.Message;
import com.chatAssistant.domain.User;
import com.chatAssistant.service.ChatGptService;
import com.chatAssistant.service.ConversationLogService;
import com.chatAssistant.service.SearchLogService;
import com.chatAssistant.service.UserService;
import com.chatAssistant.utils.ResultUtils;
import com.chatAssistant.utils.TimeUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
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
    private SearchLogService searchLogService;

    @Autowired
    private UserService userService;

    @Autowired
    private ConversationLogService conversationLogService;

    /**
     * 获取聊天内容
     * @param messages 消息列表
     * @return 聊天内容
     */
    @PostMapping("")
    public Result<Message> getChat(@RequestBody List<Message> messages,HttpServletRequest request){
        String searchId = request.getParameter("searchId");
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
            Message message = messages.get(size - 1);
            boolean insert = conversationLogService.insert(message, searchId);
        }else {
            throw new RuntimeException("消息列表为空");
        }
        Message chat = chatService.getChat(messages);
        boolean insert = conversationLogService.insert(chat,searchId);
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
    public Result<List<String>> getConversation(){
        SaSession session = StpUtil.getSession();
        Integer uid = (Integer) session.get("uid");
        List<String> allSearchIdByUid = searchLogService.getAllSearchIdByUid(uid);
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

}
