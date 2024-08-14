package com.abiliu.notify.controllers.advice;

import com.abiliu.notify.models.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.abiliu.notify.controllers"})
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel handleBadRequestException()
}
