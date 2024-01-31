package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    Jedis jedis() {
        return new Jedis(redisHost, redisPort);
    }

    @Bean
    JedisPool jedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        
        return new JedisPool(poolConfig, redisHost, redisPort);
    }
}
