package com.dayuanit.movie.movie.service.impl;

import com.dayuanit.movie.movie.entity.Film;
import com.dayuanit.movie.movie.mapper.FilmMapper;
import com.dayuanit.movie.movie.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmMapper filmMapper;

    @Override
    public List<Film> showFilmList() {
        return filmMapper.selectFilm();
    }

    @Override
    public Film showFilm(int filmId){
        return filmMapper.selectByPrimaryKey(filmId);
    }

    public Film selectFilmByFilmCode(String filmCode){
        return filmMapper.selectFilmByFilmCode(filmCode);
    }
}
