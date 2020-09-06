package com.xizi.service;

import com.xizi.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    //用户注册
    void register(User user);

    //用户登录
    User login(User user);
}


