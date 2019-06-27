package com.dayuanit.movie.movie.service;

import com.dayuanit.movie.movie.entity.Film;
import com.dayuanit.movie.movie.entity.FilmCinema;

import java.util.List;

public interface FilmCinemaService {

    List<Film> listFilmCodeByCinemaCode(String cinemaCode);
}
