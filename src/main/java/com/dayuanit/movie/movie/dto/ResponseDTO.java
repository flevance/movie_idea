package com.dayuanit.movie.movie.dto;

import java.io.Serializable;

public class ResponseDTO implements Serializable {

    //1000成功
    //666重新登录
    //错误代码9999

    private int code;
    private String message;
    private Object data;

    public ResponseDTO(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private ResponseDTO() {
    }

    public ResponseDTO(String message) {
        this.message = message;
    }

    private ResponseDTO(Object data) {
        this.code = 1000;
        this.data = data;
    }

    private ResponseDTO(int code){
        this.code = code;
    }

    public static ResponseDTO success(){
        return new ResponseDTO(1000);
    }

    public static ResponseDTO fail(){
        return new ResponseDTO(9999);
    }

    public static ResponseDTO login_code(){
        return new ResponseDTO(666);
    }

    public static ResponseDTO success(Object data){
        ResponseDTO responseDTO = new ResponseDTO(data);
        return responseDTO;
    }

    public static ResponseDTO success(Object... data){
        ResponseDTO responseDTO = new ResponseDTO(data);
        return responseDTO;
    }

    public static ResponseDTO fail(String message){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.message = message;
        responseDTO.code = 9999;
        return responseDTO;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
