package com.dayuanit.movie.movie.service;

import com.dayuanit.movie.movie.entity.FilmInfo;

import java.util.List;
import java.util.Map;

public interface FilmInfoService {

    Map<String, List<FilmInfo>> showFilmInfo(int filmId);


}
