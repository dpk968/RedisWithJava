package com.example.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class JedisService {

    @Autowired
    private Jedis jedis;

    @Autowired
    private JedisPool jedisPool;

    public String getValue(String key) {
        return jedis.get(key);
    }

    public void setValue(String key, String value) {
        jedis.set(key, value);
    }
    
    public String setValue(String key, Map<String,String> value) {
    	return jedis.hmset(key, value);
    }
    
    public String getCust(String key,String field) {
        return jedis.hget(key,field);
    }
    
    public Long delCust(String key) {
    	return jedis.del(key);
    }

    public String getValueFromPool(String key) {
        try (Jedis jedisFromPool = jedisPool.getResource()) {
            return jedisFromPool.get(key);
        }
    }

    public void setValueToPool(String key, String value) {
        try (Jedis jedisFromPool = jedisPool.getResource()) {
            jedisFromPool.set(key, value);
        }
    }
}

