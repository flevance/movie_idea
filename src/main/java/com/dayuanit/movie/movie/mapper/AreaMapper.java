package com.dayuanit.movie.movie.mapper;

import com.dayuanit.movie.movie.entity.Area;

import java.util.List;

public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

    List<Area> selectAreaList();

    Area selectAreaByAreaId(int areaId);

}