package com.dayuanit.movie.movie.controller;

import com.dayuanit.movie.movie.dto.ResponseDTO;
import com.dayuanit.movie.movie.entity.*;
import com.dayuanit.movie.movie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

@RestController
public class FilmController extends BaseController {

    @Autowired
    private FilmService filmService;
    @Autowired
    private FilmInfoService filmInfoService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private CinemaService cinemaService;
    @Autowired
    private FilmScheduleService filmScheduleService;
    @Autowired
    private FilmCinemaService filmCinemaService;
    @Autowired
    private OrderInfoService orderInfoService;
    @Value("${picture.url}")
    private String pictureUrl;


    /**
     * 显示电影列表
     */
    @RequestMapping(value = "/film/showFilmList")
    @ResponseBody
    public ResponseDTO showFilmList(){
        List<Film> films = filmService.showFilmList();
        return ResponseDTO.success(films);
    }

    /**
     * 在电影列表页面中展示电影的图片
     * @param filmName
     * @param response
     */
    @RequestMapping(value = "/picture/film/{filmName}" ,method = RequestMethod.GET)
    public void showFilmPic(@PathVariable String filmName, HttpServletResponse response){
        response.setContentType("image/png");
//        URL resource = FilmController.class.getClassLoader().getResource("static");
//        String url = resource.getPath();
        ///Users/liu/Java_Study/spider/temp
        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("static/images/picture/film/" + filmName);
             OutputStream os = response.getOutputStream()) {
            //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ url);
            byte[] buff = new byte[1024];
            int length = -1;
            while (-1 != (length = resourceAsStream.read(buff))){
                os.write(buff,0,length);
                os.flush();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    @RequestMapping(value = "/picture/actor/{filmPic}" ,method = RequestMethod.GET)
    public void showActorPic(@PathVariable String filmPic, HttpServletResponse response){
        response.setContentType("image/png");
        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("static/images/picture/actor/" + filmPic);
             OutputStream os = response.getOutputStream()) {
            byte[] buff = new byte[1024];
            int length = -1;
            while (-1 != (length = resourceAsStream.read(buff))){
                os.write(buff,0,length);
                os.flush();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }
    }

    @RequestMapping(value = "/picture/cinema/{cinemaCode}" ,method = RequestMethod.GET)
    public void showCinemaPic(@PathVariable String cinemaCode, HttpServletResponse response){
        response.setContentType("image/png");
        try (InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("static/images/picture/cinema/" + cinemaCode);
             OutputStream os = response.getOutputStream()) {
            byte[] buff = new byte[1024];
            int length = -1;
            while (-1 != (length = resourceAsStream.read(buff))){
                os.write(buff,0,length);
                os.flush();
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

    }


    @RequestMapping(value = "/film/showFilm/{filmId}",method = RequestMethod.GET)
    public ResponseDTO showFilm(@PathVariable int filmId){
        Film film = filmService.showFilm(filmId);
        return ResponseDTO.success(film);
    }


    @RequestMapping(value = "/film/showFilmInfo/{filmId}",method = RequestMethod.GET)
    public ResponseDTO showFilmInfo(@PathVariable int filmId){
        Map<String, List<FilmInfo>> stringListMap = filmInfoService.showFilmInfo(filmId);
        return ResponseDTO.success(stringListMap);
    }

    /**
     * 在购票页面显示区域列表
     */
    @RequestMapping(value = "/film/showAreaList",method = RequestMethod.GET)
    public ResponseDTO showAreaList(){
        List<Area> areas = areaService.showAreaList();
        return ResponseDTO.success(areas);
    }

    /**
     * 在购票页面根据电影ID和区域ID显示影院列表
     * @param filmId 电影的ID
     * @param areaId    区域的area_id
     */
    @RequestMapping(value = "/film/listCinema4Area/{filmId}/{areaId}",method = RequestMethod.GET)
    public ResponseDTO listCinema4Area(@PathVariable int filmId,@PathVariable int areaId){
        Film film = filmService.showFilm(filmId);
        Area area = areaService.showAreaByAreaId(areaId);
        List<Cinema> cinemas = cinemaService.selectCinemaByFilmCode(film.getFilmCode(),area == null ? -1 : area.getAreaId());
        return ResponseDTO.success(cinemas);
    }

    /**
     * 显示影院信息
     * @param cinemaCode 根据影院的cinemaCode查找
     * @return
     */
    @RequestMapping(value = "/film/selectCinema/{cinemaCode}",method = RequestMethod.GET)
    public ResponseDTO selectCinema(@PathVariable int cinemaCode){
        Cinema cinema = cinemaService.selectCinemaByCinemaCode(cinemaCode);
        return ResponseDTO.success(cinema);
    }

    @RequestMapping(value = "/film/selectCinemaByCinemaId/{cinemaId}",method = RequestMethod.GET)
    public ResponseDTO selectCinemaByCinemaId(@PathVariable int cinemaId){
        Cinema cinema = cinemaService.selectCinemaByCinemaId(cinemaId);
        return ResponseDTO.success(cinema);
    }

    /**
     * 根据电影的filmCode查询电影
     * @param filmCode
     * @return
     */
    @RequestMapping(value = "/film/selectFilmByFilmCode/{filmCode}",method = RequestMethod.GET)
    public ResponseDTO selectFilmByFilmCode(@PathVariable String filmCode){
        Film film = filmService.selectFilmByFilmCode(filmCode);
        return ResponseDTO.success(film);
    }

    @RequestMapping(value = "/film/listShowTime/{cinemaCode}/{filmCode}",method = RequestMethod.GET)
    public ResponseDTO listShowTime(@PathVariable String cinemaCode,@PathVariable String filmCode){
        List<FilmSchedule> filmSchedules = filmScheduleService.listShowTime(cinemaCode, filmCode);
        return ResponseDTO.success(filmSchedules);
    }

    @RequestMapping(value = "/film/listFilmCodeByCinemaCode/{filmCode}",method = RequestMethod.GET)
    public ResponseDTO listFilmCodeByCinemaCode(@PathVariable String filmCode){
        List<Film> films = filmCinemaService.listFilmCodeByCinemaCode(filmCode);
        return ResponseDTO.success(films);
    }

    /**
     * 根据场次购票
     */
    @RequestMapping(value = "/film/chooseTicket/{filmScheduleId}",method = RequestMethod.GET)
    public void buyTicket(@PathVariable int filmScheduleId){

    }

    /**
     * 根据场次的ID查询电影和影院
     * @param filmScheduleId 场次的ID
     */
    @RequestMapping(value = "/film/selectFilmCinemaByFilmScheduleId/{filmScheduleId}",method = RequestMethod.GET)
    public ResponseDTO selectFilmCinemaByFilmScheduleId(@PathVariable int filmScheduleId){
        FilmSchedule filmSchedule = filmScheduleService.selectFilmCinemaByFilmScheduleId(filmScheduleId);
        Film film = filmService.selectFilmByFilmCode(filmSchedule.getFilmCode());
        Cinema cinema = cinemaService.selectCinemaByCinemaCode(Integer.valueOf(filmSchedule.getCinemaCode()));
        return ResponseDTO.success(film,cinema,filmSchedule);
    }

    @RequestMapping(value = "/film/loadChooseSeats/{orderId}")
    public ResponseDTO loadChooseSeats(@PathVariable int orderId){
        String s = orderInfoService.loadChooseSeats(orderId);
        return ResponseDTO.success(s);
    }

    @RequestMapping(value = "/film/alreadyOrderSeats/{filmScheduleId}")
    public ResponseDTO alreadyOrderSeats(@PathVariable int filmScheduleId){
        return ResponseDTO.success(orderInfoService.alreadyOrderSeats(filmScheduleId));
    }


}
