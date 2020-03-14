package com.ecnu.refactoring.service;

import com.ecnu.refactoring.dao.User;
import com.ecnu.refactoring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public List<String> selectByUsername(String username){
        return userMapper.select(username);
    }

    public int insert(User user){
        return userMapper.insert(user.getUsername(),user.getPassword(),user.getEmail(),user.getCompany());
    }
}
