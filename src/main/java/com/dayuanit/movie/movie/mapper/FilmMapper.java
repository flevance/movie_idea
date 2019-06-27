package com.dayuanit.movie.movie.mapper;

import com.dayuanit.movie.movie.entity.Film;

import java.util.List;

public interface FilmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Film record);

    int insertSelective(Film record);

    Film selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Film record);

    int updateByPrimaryKeyWithBLOBs(Film record);

    int updateByPrimaryKey(Film record);

    List<Film> selectFilm();

    Film selectFilmByFilmCode(String filmCode);

}