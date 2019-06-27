package com.dayuanit.movie.movie.service.impl;

import com.dayuanit.movie.movie.entity.OrderInfo;
import com.dayuanit.movie.movie.mapper.OrderInfoMapper;
import com.dayuanit.movie.movie.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public String loadChooseSeats(int orderId) {
        List<OrderInfo> orderInfos = orderInfoMapper.selectByOrderId(orderId);
        StringBuilder sb = new StringBuilder();
        for (OrderInfo orderInfo : orderInfos){
            sb.append(orderInfo.getSeatRow()).append("-").append(orderInfo.getSeatCol()).append(",");
        }
        return sb.toString().substring(0,sb.toString().length()-1);
    }

    @Override
    public List<OrderInfo>  alreadyOrderSeats(int filmScheduleId) {
        return orderInfoMapper.selectByFilmScheduleId(filmScheduleId);
    }
}
