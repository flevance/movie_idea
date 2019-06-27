package com.dayuanit.movie.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    public String toIndex(){
        return "index";
    }

    @RequestMapping(value = "/map")
    @ResponseBody
    public Map user(){
        HashMap<String, String> map = new HashMap<>();
        map.put("aa","aaa");
        map.put("bb","bbb");
        return map;
    }

}
