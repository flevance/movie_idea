package com.dayuanit.movie.movie.service.impl;

import com.dayuanit.movie.movie.entity.FilmSchedule;
import com.dayuanit.movie.movie.mapper.FilmScheduleMapper;
import com.dayuanit.movie.movie.service.FilmScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmScheduleServiceImpl implements FilmScheduleService {

    @Autowired
    private FilmScheduleMapper filmScheduleMapper;

    public List<FilmSchedule> listShowTime(String cinemaCode,String filmCode){
        return filmScheduleMapper.selectByCinemaCodeAndFilmCode(cinemaCode, filmCode);
    }

    @Override
    public FilmSchedule selectFilmCinemaByFilmScheduleId(int filmScheduleId) {
        return filmScheduleMapper.selectByPrimaryKey(filmScheduleId);
    }
}
