package com.dayuanit.movie.movie.service;

import com.dayuanit.movie.movie.entity.Film;

import java.util.List;

public interface FilmService {

    List<Film> showFilmList();

    Film showFilm(int filmId);

    Film selectFilmByFilmCode(String filmCode);

}
