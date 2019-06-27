package com.dayuanit.movie.movie.controller;

import com.dayuanit.movie.movie.entity.Film;
import com.dayuanit.movie.movie.entity.Order;
import com.dayuanit.movie.movie.service.FilmService;
import com.dayuanit.movie.movie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PageController extends BaseController {

    @Autowired
    private FilmService filmService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/filmList")
    public String toFilm(){
        return "filmList";
    }

    @RequestMapping(value = "/")
    public String toFilm1(){
        return "filmList";
    }


    @RequestMapping(value = "/film/{filmId}")
    public String toFilmInfo(@PathVariable int filmId, HttpServletRequest request){
        Film film = filmService.showFilm(filmId);
        request.setAttribute("filmCode",film.getFilmCode());
        return "film";
    }

    @RequestMapping(value = "/film/filmCinemaInfo/{filmId}")
    public String toFilmCinemaInfo(@PathVariable int filmId,HttpServletRequest request){
        Film film = filmService.showFilm(filmId);
        request.setAttribute("filmCode",film.getFilmCode());
        return "filmCinemaInfo";
    }

    @RequestMapping(value = "/film/toChooseScene/{cinemaCode}/{filmCode}")
    public String toCinemaScene(@PathVariable String cinemaCode,@PathVariable String filmCode,HttpServletRequest request){
        request.setAttribute("cinemaCode",cinemaCode);
        request.setAttribute("filmCode",filmCode);
        return "chooseScene";
    }

    @RequestMapping(value = "/film/toChooseTicket/{filmScheduleId}")
    public String toChooseTicket(@PathVariable int filmScheduleId,HttpServletRequest request){
        request.setAttribute("filmScheduleId",filmScheduleId);
        return "chooseTicket";
    }

    @RequestMapping(value = "/film/toSettlement/{filmCode}/{cinemaCode}/{filmScheduleId}")
    public String toSettlement(@PathVariable int filmScheduleId,@PathVariable int filmCode,@PathVariable int cinemaCode,String chooseSeats,HttpServletRequest request){
        System.out.println(chooseSeats);
        request.setAttribute("chooseSeats",chooseSeats);
        return "settlement";
    }

    @RequestMapping(value = "/film/toSuccess")
    public String toSuccess(HttpServletRequest request){
        String orderId = request.getParameter("orderId");
        Order order = orderService.selectByOrderId(Integer.valueOf(orderId));
        request.setAttribute("filmId",order.getFilmId());
        request.setAttribute("cinemaId",order.getCinemaId());
        request.setAttribute("filmScheduleId",order.getFilmScheduleId());
        request.setAttribute("orderId",order.getId());
        return "success";
    }
}
