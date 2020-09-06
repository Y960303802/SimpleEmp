package com.xizi.dao;

import com.xizi.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper   //创建Dao对象
public interface UserDao {
    void save(User user);

    User findByUserName(String username);

}
