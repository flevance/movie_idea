package com.dayuanit.movie.movie.mapper;

import com.dayuanit.movie.movie.entity.Cinema;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CinemaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cinema record);

    int insertSelective(Cinema record);

    Cinema selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cinema record);

    int updateByPrimaryKey(Cinema record);

    List<Cinema> selectCinemaByFilmCode(@Param("filmCode") String filmCode, @Param("areaId") int areaId);

    Cinema selectCinemaByCinemaCode(int CinemaCode);
}