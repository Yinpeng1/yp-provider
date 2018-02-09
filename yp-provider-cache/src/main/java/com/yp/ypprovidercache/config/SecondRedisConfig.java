package com.yp.ypprovidercache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@PropertySource("classpath:/redis.properties")
@EnableCaching
@Configuration
public class SecondRedisConfig extends RedisConfig{
    @Value("${spring.redis2.database}")
    private int dbIndex;
    @Value("${spring.redis2.host}")
    private String host;
    @Value("${spring.redis2.port}")
    private int port;
    @Value("${spring.redis2.timeout}")
    private int timeout;


    @Bean
    public JedisConnectionFactory articleRedisConnectionFactory() {
        return jedisConnectionFactory(dbIndex,host, port, timeout);
    }


    @Bean(name = "SecondRedisTemplate")
    public RedisTemplate articleRedisTemplate() {
        return super.redisTemplate(articleRedisConnectionFactory());
    }


    @Bean(name = "SecondStringRedisTemplate")
    public StringRedisTemplate articleStringRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(articleRedisConnectionFactory());
        template.afterPropertiesSet();
        return template;
    }
}
