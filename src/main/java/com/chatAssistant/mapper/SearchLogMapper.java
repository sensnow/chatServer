package com.chatAssistant.mapper;

import com.chatAssistant.domain.SearchLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

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

}




