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
    @Select("select * from user where uid = #{uid}")
    public User getUserByUid(long uid);

    @ResultMap("UserResultMap")
    @Select("select * from user where uid in (#{ids}")
    public List<User> getUserByIds(List<Long> ids);

    @ResultMap("UserResultMap")
    @Insert("insert into user(userName,password) values(#{userName},#{password})")
    public int insertUser(String userName, String password);*/


    @Select("select * from user where user_name = #{userName}")
    User getUserByUserName(String userName);

    @Select("select * from user where uid = #{uid}")
    public User getUserByUid(long uid);

    @Select("select count(*) from user where user_name = #{userName}")
    public int checkUserName(String userName);

    @Select("select * from user")
    List<User> selectAll();

    @Select("select is_available from user where uid = #{uid}")
    Integer getIsAvailable(Integer uid);

    @Select("select * from user where user_name = #{userName} and password = #{password} and is_available != 0")
    User login(String userName, String password);

    @Select("select user_name from user where uid = #{uid} and is_available != 0")
    String getUserNameByUid(Integer uid);

    @Update("update user set total_tokens = total_tokens + #{totalTokens} where uid = #{uid}")
    int updateTotalTokens(Integer uid, Integer totalTokens);

}
