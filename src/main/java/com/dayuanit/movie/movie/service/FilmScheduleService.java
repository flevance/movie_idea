package com.dayuanit.movie.movie.service;

import com.dayuanit.movie.movie.entity.FilmSchedule;

import java.util.List;

public interface FilmScheduleService {

    List<FilmSchedule> listShowTime(String cinemaCode, String filmCode);

    FilmSchedule selectFilmCinemaByFilmScheduleId(int filmScheduleId);
}
