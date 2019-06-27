package com.dayuanit.movie.movie.handler;

import com.dayuanit.movie.movie.entity.Order;
import com.dayuanit.movie.movie.enums.OrderStatusEnum;
import com.dayuanit.movie.movie.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class OrderCancelHandler {

    @Autowired
    private OrderMapper orderMapper;

    public boolean orderCancel(Order order){
        int i = orderMapper.updateStatus(order.getId(), order.getStatus(), OrderStatusEnum.支付取消.getK());
        if (i != 1){
            return false;
        }
        return true;

    }

}
