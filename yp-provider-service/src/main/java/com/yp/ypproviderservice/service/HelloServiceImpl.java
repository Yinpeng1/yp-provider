package com.yp.ypproviderservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yp.apiservice.service.HelloService;
import com.yp.ypprovidermapper.Entity.User;
import com.yp.ypprovidermapper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service(protocol="dubbo-hessian")
public class HelloServiceImpl implements HelloService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String sayHello() {
        return "Hello World";
    }

    @Override
    public void add() {
        User user = new User();
        user.setName("xxxxx");
        user.setPassword("12345567");
        userMapper.add(user);
    }

}
