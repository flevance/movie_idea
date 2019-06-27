package com.dayuanit.movie.movie.controller;

import com.dayuanit.movie.movie.dto.ResponseDTO;
import com.dayuanit.movie.movie.exception.BizException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(BizException.class)
    public ResponseDTO bizExceptionHandler(BizException be){
        logger.error(be.getMessage(),be);
        return ResponseDTO.fail(be.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseDTO exceptionHandler(Exception e){
        logger.error(e.getMessage(),e);
        return ResponseDTO.fail(e.getMessage());
    }
}
