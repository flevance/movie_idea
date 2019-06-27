package com.dayuanit.movie.movie.service;

public interface RedisService {

    boolean cacheSeatInfo(int filmScheduleId,int row,int col);
}
