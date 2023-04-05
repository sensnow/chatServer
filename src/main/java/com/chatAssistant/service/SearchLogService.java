package com.chatAssistant.service;

import com.chatAssistant.domain.SearchLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author sensnow
* @description 针对表【search_log】的数据库操作Service
* @createDate 2023-04-04 19:35:54
*/
public interface SearchLogService extends IService<SearchLog> {

        /**
        * 插入一条搜索日志
        * @param uid 用户id
        * @param date 搜索时间
        * @return 插入的行数
        */
        boolean insert(String searchId,Integer uid, String date);
}
