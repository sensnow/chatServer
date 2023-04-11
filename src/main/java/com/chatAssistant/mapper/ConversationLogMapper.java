package com.chatAssistant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chatAssistant.domain.ConversationLog;
import com.chatAssistant.domain.Message;
import org.apache.ibatis.annotations.Delete;
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


//    @Delete("delete from conversation_log where cid = (select max(c2.cid) from conversation_log c2 where c2.search_id = #{searchId})")

    @Delete("DELETE FROM conversation_log WHERE cid = (SELECT * from (SELECT MAX(t.cid) FROM conversation_log t WHERE t.search_id = #{searchId}) as t2)")
    Integer deleteLastMsg(String searchId);

}




