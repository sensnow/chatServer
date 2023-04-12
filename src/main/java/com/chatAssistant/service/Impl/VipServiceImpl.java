package com.chatAssistant.service.Impl;

import com.chatAssistant.domain.User;
import com.chatAssistant.service.SearchLogService;
import com.chatAssistant.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VipServiceImpl implements VipService {

    @Autowired
    private SearchLogService searchLogService;

    public final static Map<Integer,User> UserList = new ConcurrentHashMap<>();

    @Override
    public boolean isVip(String searchId) {
        Integer uid = searchLogService.getBySearchId(searchId).getUid();
        if(uid == null){
            return false;
        }
        Integer isAvailable = UserList.get(uid).getIsAvailable();
        return isAvailable !=4 && isAvailable != 0;
    }
}
