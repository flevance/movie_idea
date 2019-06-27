package com.dayuanit.movie.movie.service.impl;

import com.dayuanit.movie.movie.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean cacheSeatInfo(int filmScheduleId,int row,int col){
        String key = "movie:ticket:" + filmScheduleId + ":" + row + ":" + col;
        return redisTemplate.opsForValue().setIfAbsent(key, "", 60, TimeUnit.SECONDS);
    }
}
