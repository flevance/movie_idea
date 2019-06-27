package com.dayuanit.movie.movie.service;

import com.dayuanit.movie.movie.entity.Area;

import java.util.List;

public interface AreaService {

    List<Area> showAreaList();

    Area showAreaByAreaId(int areaId);
}
