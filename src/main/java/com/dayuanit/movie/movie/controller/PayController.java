package com.dayuanit.movie.movie.controller;

import com.dayuanit.movie.movie.dto.ResponseDTO;
import com.dayuanit.movie.movie.entity.Order;
import com.dayuanit.movie.movie.handler.AlipayHandler;
import com.dayuanit.movie.movie.service.FilmService;
import com.dayuanit.movie.movie.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Controller
public class PayController extends BaseController{

    @Autowired
    private OrderService orderService;
    @Autowired
    private FilmService filmService;
    @Autowired
    private AlipayHandler alipayHandler;

    @ResponseBody
    @RequestMapping(value = "/film/toPay/{filmCode}/{cinemaCode}/{filmScheduleId}/{chooseSeats}")
    public ResponseDTO toPay(@PathVariable int filmCode, @PathVariable int cinemaCode,@PathVariable int filmScheduleId ,@PathVariable String chooseSeats){
        Order order = orderService.creatOrder(filmCode, cinemaCode, filmScheduleId, chooseSeats);
        String form = alipayHandler.alipayProcess(order);
        return ResponseDTO.success(form);
    }

    @ResponseBody
    @RequestMapping(value = "/pay/alipayNotify")
    public void alipayNotify(HttpServletRequest request, HttpServletResponse response){
        System.out.println("alipay notify...");
        try (OutputStream os = response.getOutputStream()) {
            alipayHandler.alipayNotify(request.getParameterMap());
            os.write("success".getBytes());
            os.flush();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }
}