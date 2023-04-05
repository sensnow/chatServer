package com.chatAssistant.mapper;

import com.chatAssistant.domain.LoginLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
* @author sensnow
* @description 针对表【login_log】的数据库操作Mapper
* @createDate 2023-04-04 19:35:54
* @Entity com.chatAssistant.domain.LoginLog
*/

@Mapper
public interface LoginLogMapper extends BaseMapper<LoginLog> {

    @Insert("insert into login_log(login_time,login_ip,uid) values(#{loginTime},#{ip},#{uid})")
    int insert(LoginLog loginLog);

}




