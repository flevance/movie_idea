package com.dayuanit.movie.movie.task;

import com.dayuanit.movie.movie.entity.Order;
import com.dayuanit.movie.movie.enums.OrderStatusEnum;
import com.dayuanit.movie.movie.exception.BizException;
import com.dayuanit.movie.movie.handler.AlipayHandler;
import com.dayuanit.movie.movie.handler.OrderCancelHandler;
import com.dayuanit.movie.movie.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;

@Component
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AlipayHandler alipayHandler;
    @Autowired
    private OrderCancelHandler orderCancelHandler;

    @Scheduled(fixedRate = 5000)
    public void task(){
        orderCancelProcess();
        orderQueryProcess();
    }

    private void orderQueryProcess(){
        List<Order> orders = orderMapper.selectByStatus(OrderStatusEnum.待付款.getK());

        for (Order order : orders){
            try {
                alipayHandler.alipayQuery(order);
            }catch (BizException e){
                e.printStackTrace();
            }
        }
    }

    private void orderCancelProcess(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,-2);
        List<Order> orders = orderMapper.selectByStatusWithTime(OrderStatusEnum.待付款.getK(),calendar.getTime());

        for (Order order : orders){
            try {
                boolean b = orderCancelHandler.orderCancel(order);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
