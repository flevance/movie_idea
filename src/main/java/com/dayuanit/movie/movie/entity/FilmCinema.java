package com.dayuanit.movie.movie.entity;

public class FilmCinema {
    private String cinemaCode;

    private String filmCode;

    public String getCinemaCode() {
        return cinemaCode;
    }

    public void setCinemaCode(String cinemaCode) {
        this.cinemaCode = cinemaCode == null ? null : cinemaCode.trim();
    }

    public String getFilmCode() {
        return filmCode;
    }

    public void setFilmCode(String filmCode) {
        this.filmCode = filmCode == null ? null : filmCode.trim();
    }
}