package com.william.fullbankingapplicationfinal.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class HttpException extends RuntimeException {

    private String message;
    private HttpStatus status;

    public HttpException(HttpStatus status, String message){
        super(message);
        this.status = status;

    }


}
