package com.dayuanit.movie.movie.service.impl;

import com.dayuanit.movie.movie.entity.Film;
import com.dayuanit.movie.movie.entity.FilmCinema;
import com.dayuanit.movie.movie.mapper.FilmCinemaMapper;
import com.dayuanit.movie.movie.mapper.FilmMapper;
import com.dayuanit.movie.movie.service.FilmCinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmCinemaServiceImpl implements FilmCinemaService {

    @Autowired
    private FilmCinemaMapper filmCinemaMapper;
    @Autowired
    private FilmMapper filmMapper;

    @Override
    public List<Film> listFilmCodeByCinemaCode(String cinemaCode){
        List<FilmCinema> filmCinemas = filmCinemaMapper.listFilmCodeByCinemaCode(cinemaCode);
        List<Film> films = new ArrayList<>();
        for (FilmCinema filmCinema : filmCinemas){
            Film film = filmMapper.selectFilmByFilmCode(filmCinema.getFilmCode());
            films.add(film);
        }
        return films;
    }

}
