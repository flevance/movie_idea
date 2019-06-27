package com.dayuanit.movie.movie.mapper;

import com.dayuanit.movie.movie.entity.FilmSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilmScheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FilmSchedule record);

    int insertSelective(FilmSchedule record);

    FilmSchedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FilmSchedule record);

    int updateByPrimaryKey(FilmSchedule record);

    List<FilmSchedule> selectByCinemaCodeAndFilmCode(@Param("cinemaCode") String cinemaCode, @Param("filmCode") String filmCode);
}