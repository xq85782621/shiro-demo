package com.meizhi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meizhi.mapper.UserMapper;
import com.meizhi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    public User findUserByUsernameAndPassword(String username, String password){
        User user = userMapper.selectOne(new QueryWrapper<>(new User()).eq("username", username).eq("password", password));
        return user;
    }

}
