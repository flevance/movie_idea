package com.dayuanit.movie.movie.mapper;

import com.dayuanit.movie.movie.entity.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int updateStatus(@Param("orderId") Integer orderId, @Param("oldStatus") Integer oldStatus, @Param("newStatus") Integer newStatus);

    List<Order> selectByStatus(int status);

    List<Order> selectByStatusWithTime(@Param("status") int status, @Param("delayTime") Date delayTime);


}