package com.xizi.service;

import com.xizi.dao.EmpDao;
import com.xizi.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class EmpServiceImpl  implements  EmpService{

    @Autowired
    private EmpDao empDao;


    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Emp> findAll() {
        return empDao.findAll();
    }

    @Override
    public void save(Emp emp) {
        empDao.save(emp);
    }

    @Override
    public void delete(String id) {
        empDao.delete(id);
    }

    @Override
    public Emp findOne(String id) {
        Emp one = empDao.findOne(id);
        return one;
    }

    @Override
    public void update(Emp emp) {
        empDao.update(emp);
    }
}
