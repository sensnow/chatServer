package com.chatAssistant.mapper;

import com.chatAssistant.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {


    @Select("select * from user where userName = #{userName} and password = #{password}")
    public int login(String userName, String password);

    @Select("select * from user where userName = #{userName}")
    public User getUserByUserName(String userName);

    @Select("select * from user where uid = #{uid}")
    public User getUserByUid(long uid);

    @Select("select * from user where uid in (#{ids}")
    public List<User> getUserByIds(List<Long> ids);

    @Insert("insert into user(userName,password) values(#{userName},#{password}")
    public int insertUser(User user);

}
