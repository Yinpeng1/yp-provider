package com.yp.ypprovidercache.cacheService;

import com.yp.apientity.Entity.User;
import com.yp.ypprovidermapper.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service("userCache")
public class UserCache {

    @Autowired
    private UserMapper userMapper;

    @Resource(name = "FirstRedisTemplate")
    private RedisTemplate<String, User> redisTemplate;

//    @Transactional(如果对redis进行多次非读操作需要加上事物)
    public User getEntity(String name){
        if (redisTemplate.opsForValue().get(name) == null) {
            System.out.println("库中获取");
            User user = userMapper.get(name);
            redisTemplate.opsForValue().set(user.getName(),user,60, TimeUnit.SECONDS);
            return user;
        }
        System.out.println("缓存获取");
        return redisTemplate.opsForValue().get(name);
    }
}
