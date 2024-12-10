package com.example.discord.services;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    
    @CachePut(value = "myCache", key = "#key")
    public String setValue(String key, String value) {
        return value;
    }

    @Cacheable(value = "myCache", key = "#key")
    public String getValue(String key) {
        return null; 
    }
}
