package com.ecnu.redo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
        @Insert("INSERT INTO user(username,password,email,company) VALUES (#{username},#{password},#{email},#{company})")
    public int insert(@Param("username") String username, @Param("password" ) String password ,
                      @Param ("email") String email, @Param("company") String company);

    @Select("select password from user where username=#{username}")
    public List<String> select(@Param("username") String username);
}
