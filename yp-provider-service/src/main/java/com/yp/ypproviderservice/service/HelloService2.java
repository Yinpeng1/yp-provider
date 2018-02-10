package com.yp.ypproviderservice.service;


import com.yp.apientity.Entity.User;
import com.yp.ypprovidermapper.mapper2.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HelloService2 {

    @Autowired
    private User2Mapper user2Mapper;

    @Transactional
    public void add(User user){
        user2Mapper.add(user);
    }
}
