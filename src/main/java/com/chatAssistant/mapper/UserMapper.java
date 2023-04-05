package com.chatAssistant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chatAssistant.domain.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

/*    @ResultMap("UserResultMap")
    @Select("select * from user where user = #{userName} and password = #{password}")
    public User login(String userName, String password);

    @ResultMap("UserResultMap")
    @Select("select * from user where userName = #{userName}")
    public User getUserByUserName(String userName);

    @ResultMap("UserResultMap")
    @Select("select * from user where uid = #{uid}")
    public User getUserByUid(long uid);

    @ResultMap("UserResultMap")
    @Select("select * from user where uid in (#{ids}")
    public List<User> getUserByIds(List<Long> ids);

    @ResultMap("UserResultMap")
    @Insert("insert into user(userName,password) values(#{userName},#{password})")
    public int insertUser(String userName, String password);*/

    @Select("select count(*) from user where userName = #{userName}")
    public int checkUserName(String userName);

}
