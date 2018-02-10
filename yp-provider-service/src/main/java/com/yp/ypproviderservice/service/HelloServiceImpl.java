package com.yp.ypproviderservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yp.apientity.Entity.User;
import com.yp.apiservice.service.HelloService;
import com.yp.ypprovidercache.cacheService.UserCache;
import com.yp.ypprovidermapper.mapper.UserMapper;
import com.yp.ypprovidermapper.mapper2.User2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(protocol="dubbo-hessian")
public class HelloServiceImpl implements HelloService {

    @Autowired
    private User2Mapper user2Mapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserCache userCache;

    @Autowired
    private HelloService2 helloService2;
    @Override
    public String sayHello() {
        return "Hello World";
    }

    @Override
    /**开启事务*/
    @Transactional
    public void add() {
        User user = new User();
        user.setName("xxxxx");
        user.setPassword("12345567");
        userMapper.add(user);
//        helloService2.add(user);
//        user2Mapper.add(user);
        int i = 1/0;
    }

    @Override
    public User get(String s) {
        return userCache.getEntity(s);
    }


}
