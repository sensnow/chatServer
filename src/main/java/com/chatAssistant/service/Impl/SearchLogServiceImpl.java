package com.chatAssistant.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chatAssistant.domain.SearchLog;
import com.chatAssistant.mapper.ConversationLogMapper;
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

    @Autowired
    private ConversationLogMapper conversationLogMapper;

    @Override
    public boolean insert( String searchId,Integer uid, String date) {
        return searchLogMapper.insert(new SearchLog(searchId, date, uid,null))==1;
    }

    @Override
    public int getSearchLogCount(String searchId) {
        return searchLogMapper.getSearchLogCount(searchId);
    }

    @Override
    public List<String> getAllSearchIdByUid(Integer uid) {
        return searchLogMapper.getAllSearchIdByUid(uid);
    }

    @Override
    public List<SearchLog> getAllSearchLogByUid(Integer uid) {
        return searchLogMapper.getAllSearchLogByUid(uid);
    }

    @Override
    public int deleteBySearchIdInt(String searchId) {
        if(getSearchLogCount(searchId) == 0){
            throw new RuntimeException("该搜索记录不存在");
        }else {
            return searchLogMapper.deleteBySearchIdInt(searchId);
        }
    }

    @Override
    public SearchLog getBySearchId(String searchId) {
        return searchLogMapper.getBySearchId(searchId);
    }

    @Override
    public int setDescribe(String searchId, String describe) {
        if(describe == null || describe.equals(""))
            throw new RuntimeException("描述不能为空");
        return searchLogMapper.setDescribe(searchId, describe);
    }

    @Override
    public int deleteByUid(Integer uid) {
        return searchLogMapper.deleteByUid(uid);
    }

    @Override
    public boolean updateCostTokens(String searchId, Integer costTokens) {
        return searchLogMapper.updateCostTokens(searchId, costTokens) == 1;
    }


}




