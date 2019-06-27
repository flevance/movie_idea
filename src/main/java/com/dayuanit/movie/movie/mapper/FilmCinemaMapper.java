package com.dayuanit.movie.movie.mapper;

import com.dayuanit.movie.movie.entity.FilmCinema;

import java.util.List;

public interface FilmCinemaMapper {
    int insert(FilmCinema record);

    int insertSelective(FilmCinema record);

    List<FilmCinema> listFilmCodeByCinemaCode(String cinemaCode);

}