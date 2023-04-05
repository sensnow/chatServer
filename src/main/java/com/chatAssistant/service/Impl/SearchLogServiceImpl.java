package com.chatAssistant.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatAssistant.domain.SearchLog;
import com.chatAssistant.service.SearchLogService;
import com.chatAssistant.mapper.SearchLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author sensnow
*/
@Service
public class SearchLogServiceImpl extends ServiceImpl<SearchLogMapper, SearchLog>
    implements SearchLogService{

    @Autowired
    private SearchLogMapper searchLogMapper;


    @Override
    public boolean insert( String searchId,Integer uid, String date) {
        return searchLogMapper.insert(new SearchLog(searchId, date, uid))==1;
    }

    @Override
    public int getSearchLogCount(String searchId) {
        return searchLogMapper.getSearchLogCount(searchId);
    }

    @Override
    public List<String> getAllSearchIdByUid(Integer uid) {
        return searchLogMapper.getAllSearchIdByUid(uid);
    }


}




