package com.dayuanit.movie.movie.service.impl;

import com.dayuanit.movie.movie.entity.Area;
import com.dayuanit.movie.movie.mapper.AreaMapper;
import com.dayuanit.movie.movie.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.WebEndpoint;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<Area> showAreaList(){
        return areaMapper.selectAreaList();
    }

    @Override
    public Area showAreaByAreaId(int areaId){
        return areaMapper.selectAreaByAreaId(areaId);
    }
}
