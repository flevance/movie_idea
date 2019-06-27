package com.dayuanit.movie.movie.mapper;

import com.dayuanit.movie.movie.entity.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);

    List<OrderInfo> selectByOrderId(int orderId);

    List<OrderInfo> selectByFilmScheduleId(int filmScheduleId);

    OrderInfo loadAlreadyBuySeat(@Param("filmScheduleId") int filmScheduleId, @Param("row") int row, @Param("col") int col);
}