package com.xizi.service;


import com.xizi.dao.UserDao;
import com.xizi.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {

        //根据用户输入用户名判断用户是否存在
        User byUserName = userDao.findByUserName(user.getUsername());
        if (byUserName==null){
            //1.生成用户的状态
            user.setStatus("已激活");
            //设置用户注册时间
            user.setRegisterTime(new Date());
            //调用dao
            userDao.save(user);
        }else {
            throw new RuntimeException("用户名已经存在");
        }


    }

    @Override
    public User login(User user) {
        //根据用户输入用户名进行查询
        User byUserName = userDao.findByUserName(user.getUsername());
        if (!ObjectUtils.isEmpty(byUserName)){
            //比较密码
            if (user.getPassword().equals(byUserName.getPassword())){
                return byUserName;
            }else{
                throw new RuntimeException("密码输入不正确");
            }
        }else {
            throw new RuntimeException("用户名输入错误");
        }
    }
}
