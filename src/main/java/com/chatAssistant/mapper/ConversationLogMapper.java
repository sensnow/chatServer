package com.chatAssistant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chatAssistant.domain.ConversationLog;
import com.chatAssistant.domain.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author sensnow
* @description 针对表【conservation_log】的数据库操作Mapper
* @createDate 2023-04-04 19:35:54
* @Entity com.chatAssistant.domain.ConservationLog
*/

@Mapper
public interface ConversationLogMapper extends BaseMapper<ConversationLog> {

    @Insert("insert into conversation_log(search_id,message,role) values(#{searchId},#{message},#{role})")
    int insert(ConversationLog conversationLog);

    @Select("select role,message as content from conversation_log where search_id = #{searchId} order by cid asc")
    List<Message> getMessagesBySearchId(String searchId);

}




