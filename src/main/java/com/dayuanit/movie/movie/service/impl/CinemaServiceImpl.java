package com.dayuanit.movie.movie.service.impl;

import com.dayuanit.movie.movie.entity.Cinema;
import com.dayuanit.movie.movie.mapper.CinemaMapper;
import com.dayuanit.movie.movie.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaService {

    @Autowired
    private CinemaMapper cinemaMapper;

    @Override
    public List<Cinema> selectCinemaByFilmCode(String filmCode,int areaId){
        List<Cinema> cinemas = cinemaMapper.selectCinemaByFilmCode(filmCode, areaId);
        return cinemas;
    }

    @Override
    public Cinema selectCinemaByCinemaCode(int cinemaCode){
        return cinemaMapper.selectCinemaByCinemaCode(cinemaCode);
    }

    @Override
    public Cinema selectCinemaByCinemaId(int cinemaId){
        return cinemaMapper.selectByPrimaryKey(cinemaId);
    }
}
