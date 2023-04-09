package com.chatAssistant.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JsonParseUtils {
    public static String parseJson(String inputLine) {
        // 获取choices
        JSONArray jsonArray  = (JSONArray) JSON.parseObject(inputLine.split("data: ")[0]).get("choices");
        // 获取第一个index
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        // 获取message
        JSONObject messageObject = (JSONObject) jsonObject.getInnerMap().get("delta");
        // 获取role和content
        String role = (String) messageObject.get("role");
        String content_seg = (String) messageObject.get("content");
        if(role == null){
            return content_seg;
        }else {
            return role;
        }
    }
}
