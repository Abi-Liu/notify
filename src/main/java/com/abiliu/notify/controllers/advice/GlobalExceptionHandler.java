package com.abiliu.notify.controllers.advice;

import com.abiliu.notify.exceptions.BadRequestException;
import com.abiliu.notify.exceptions.NotFoundException;
import com.abiliu.notify.exceptions.UnauthorizedException;
import com.abiliu.notify.models.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.abiliu.notify.controllers"})
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ErrorModel handleBadRequestException(BadRequestException badRequestException) {
        return new ErrorModel(badRequestException.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorModel handleNotFoundException(NotFoundException notFoundException) {
        return new ErrorModel(notFoundException.getMessage());
    }

   @ResponseStatus(HttpStatus.UNAUTHORIZED)
   @ExceptionHandler(UnauthorizedException.class)
   public ErrorModel handleUnauthorizedException(UnauthorizedException unauthorizedException) {
        return new ErrorModel(unauthorizedException.getMessage());
   }
}
