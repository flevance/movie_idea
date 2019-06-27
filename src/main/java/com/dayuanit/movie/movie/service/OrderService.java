package com.dayuanit.movie.movie.service;

import com.dayuanit.movie.movie.entity.Order;

public interface OrderService {

    Order creatOrder(int filmCode, int cinemaCode, int filmScheduleId, String chooseSeats);

    boolean processAliPayNotify(String tradeNo,String totalAmount);

    Order selectByOrderId(int orderId);

    boolean processQueryOrder(Order order,String totalAmount);


}
