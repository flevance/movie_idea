package com.dayuanit.movie.movie.exception;

import org.springframework.stereotype.Component;


public class BizException extends RuntimeException{

    public BizException(String message) {
        super(message);
    }
}
