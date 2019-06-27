package com.dayuanit.movie.movie.mapper;

import com.dayuanit.movie.movie.entity.FilmInfo;

import java.util.List;


public interface FilmInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FilmInfo record);

    int insertSelective(FilmInfo record);

    FilmInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FilmInfo record);

    int updateByPrimaryKey(FilmInfo record);

    List<FilmInfo> selectByFilmId(Integer filmId);

}