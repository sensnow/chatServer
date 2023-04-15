package com.chatAssistant.mapper;

import com.chatAssistant.domain.SearchLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author sensnow
* @description 针对表【search_log】的数据库操作Mapper
* @createDate 2023-04-04 19:35:54
* @Entity com.chatAssistant.domain.SearchLog
*/

@Mapper
public interface SearchLogMapper extends BaseMapper<SearchLog> {

    @Insert("insert into search_log(search_id,search_time,uid) values(#{searchId}},#{searchTime},#{uid})")
    int insertSearchLog(SearchLog searchLog);

    @Select("select count(*) from search_log where search_id=#{searchId}")
    int getSearchLogCount(String searchId);

    @Select("select search_id from search_log where uid=#{uid}")
    List<String> getAllSearchIdByUid(Integer uid);

    @Select("select * from search_log where uid=#{uid}")
    List<SearchLog> getAllSearchLogByUid(Integer uid);

    @Delete("delete from search_log where search_id=#{searchId}")
    int deleteBySearchIdInt(String searchId);

    @Select("select * from search_log where search_id=#{searchId}")
    SearchLog getBySearchId(String searchId);

    @Update("update search_log set search_log.`describe` = #{describe} where search_log.search_id = #{searchId}")
    int setDescribe(String searchId,String describe);


    @Delete("delete from search_log where uid=#{uid}")
    int deleteByUid(Integer uid);

    @Update("update search_log set cost_tokens = cost_tokens + #{costTokens} where search_id = #{searchId}")
    int updateCostTokens(String searchId, Integer costTokens);
}




