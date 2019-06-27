package com.dayuanit.movie.movie.service.impl;

import com.dayuanit.movie.movie.entity.FilmInfo;
import com.dayuanit.movie.movie.mapper.FilmInfoMapper;
import com.dayuanit.movie.movie.service.FilmInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilmInfoServiceImpl implements FilmInfoService {

    @Autowired
    private FilmInfoMapper filmInfoMapper;

    @Override
    public Map<String, List<FilmInfo>> showFilmInfo(int filmId) {
        List<FilmInfo> filmInfos = filmInfoMapper.selectByFilmId(filmId);
        Map<String, List<FilmInfo>> hashMap = new HashMap<>();
        ArrayList<FilmInfo> dy = new ArrayList<>();
        ArrayList<FilmInfo> yy = new ArrayList<>();

        for (FilmInfo filmInfo : filmInfos){
            if (filmInfo.getActorType() == 1){
                dy.add(filmInfo);
            }
            if (filmInfo.getActorType() == 2){
                yy.add(filmInfo);
            }
        }
        hashMap.put("dy",dy);
        hashMap.put("yy",yy);

        return hashMap;
    }
}
