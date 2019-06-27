package com.dayuanit.movie.movie.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.dayuanit.movie.movie.entity.*;
import com.dayuanit.movie.movie.enums.OrderStatusEnum;
import com.dayuanit.movie.movie.exception.BizException;
import com.dayuanit.movie.movie.mapper.*;
import com.dayuanit.movie.movie.service.OrderService;
import com.dayuanit.movie.movie.service.RedisService;
import com.dayuanit.movie.movie.utils.MoneyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private CinemaMapper cinemaMapper;
    @Autowired
    private FilmScheduleMapper filmScheduleMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private RedisService redisService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order creatOrder(int filmCode,int cinemaCode,int filmScheduleId,String chooseSeats){
        Film film = filmMapper.selectFilmByFilmCode(Integer.toString(filmCode));
        Cinema cinema = cinemaMapper.selectCinemaByCinemaCode(cinemaCode);
        FilmSchedule filmSchedule = filmScheduleMapper.selectByPrimaryKey(filmScheduleId);
        if (null == film || null==cinema || null == filmSchedule){
            throw new BizException("参数有错误");
        }
        String[] seats = chooseSeats.split(",");

        String price = MoneyUtils.mul("0.1", String.valueOf(seats.length));

        Order order = new Order();
        order.setFilmId(film.getId());
        order.setCinemaId(cinema.getId());
        order.setAmount(price);
        order.setFilmScheduleId(filmSchedule.getId());
        order.setStatus(0);
        order.setCreateTime(new Date());
        order.setModifyTime(new Date());
        int insert = orderMapper.insert(order);

        for (String seat : seats){
            //使用Redis数据库,方式高并发的情况下选座重复
            String[] seatRowCol = seat.split("-");
            boolean b = redisService.cacheSeatInfo(filmScheduleId, Integer.valueOf(seatRowCol[0]), Integer.valueOf(seatRowCol[1]));
            if (!b){
                throw new BizException("座位已被选中,请重新选座");
            }

            //TODO 查询数据库确定是否重复选座
            OrderInfo orderInfo1 = orderInfoMapper.loadAlreadyBuySeat(filmScheduleId, Integer.valueOf(seatRowCol[0]), Integer.valueOf(seatRowCol[1]));
            if (null != orderInfo1){
                throw new BizException("座位号已被选中,请重新选座");
            }


            String[] split = seat.split("-");
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrderId(order.getId());
            orderInfo.setPrice("0.1");
            orderInfo.setSeatCol(Integer.valueOf(split[1]));
            orderInfo.setSeatRow(Integer.valueOf(split[0]));
            orderInfoMapper.insert(orderInfo);
        }
        return order;
    }

    @Override
    public boolean processAliPayNotify(String tradeNo,String totalAmount){
        Order order = orderMapper.selectByPrimaryKey(Integer.parseInt(tradeNo));
        if (null == order){
            return false;
        }
        if (!order.getAmount().equals(totalAmount)){
            return false;
        }
        if (order.getStatus() != OrderStatusEnum.待付款.getK()){
            return false;
        }
        int i = orderMapper.updateStatus(order.getId(), OrderStatusEnum.待付款.getK(), OrderStatusEnum.已支付.getK());
        if (i != 1){
            return false;
        }
        return true;
    }
    @Override
    public Order selectByOrderId(int orderId){
        Order order = orderMapper.selectByPrimaryKey(orderId);
        return order;
    }

    @Override
    public boolean processQueryOrder(Order order,String totalAmount){
        Order order1 = orderMapper.selectByPrimaryKey(order.getId());
        if (!totalAmount.equals(order1.getAmount())){
            return false;
        }
        int i = orderMapper.updateStatus(order1.getId(), OrderStatusEnum.待付款.getK(), OrderStatusEnum.已支付.getK());
        if (i != 1){
            return false;
        }
        return true;
    }


}
