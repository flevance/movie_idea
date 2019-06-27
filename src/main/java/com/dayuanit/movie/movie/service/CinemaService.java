package com.dayuanit.movie.movie.service;

import com.dayuanit.movie.movie.entity.Cinema;

import java.util.List;

public interface CinemaService {

    List<Cinema> selectCinemaByFilmCode(String filmCode,int areaId);

    Cinema selectCinemaByCinemaCode(int cinemaCode);

    Cinema selectCinemaByCinemaId(int cinemaId);
}
