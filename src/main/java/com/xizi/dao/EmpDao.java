package com.xizi.dao;

import com.xizi.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface EmpDao {
    List<Emp>  findAll();

    void save(Emp emp);

    void delete(String id);

    Emp findOne(String id);


    void update(Emp emp);
}
