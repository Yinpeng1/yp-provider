package com.yp.ypprovidercache.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@EnableCaching
@PropertySource("classpath:/redis.properties")
public class FirstRedisConfig extends RedisConfig{

    @Value("${spring.redis.database}")
    private int dbIndex;
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;

    @Primary
    @Bean
    public JedisConnectionFactory defaultRedisConnectionFactory() {
        return jedisConnectionFactory(dbIndex,host, port, timeout);
    }

    @Bean(name = "FirstRedisTemplate")
    public RedisTemplate defaultRedisTemplate() {
        return super.redisTemplate(defaultRedisConnectionFactory());
    }

    @Bean(name = "FirstStringRedisTemplate")
    public StringRedisTemplate defaultStringRedisTemplate() {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(defaultRedisConnectionFactory());
        template.afterPropertiesSet();
        return template;
    }
}
