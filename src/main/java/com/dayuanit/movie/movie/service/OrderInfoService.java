package com.dayuanit.movie.movie.service;

import com.dayuanit.movie.movie.entity.OrderInfo;

import java.util.List;

public interface OrderInfoService {

    String loadChooseSeats(int orderId);

    List<OrderInfo> alreadyOrderSeats(int filmScheduleId);
}
