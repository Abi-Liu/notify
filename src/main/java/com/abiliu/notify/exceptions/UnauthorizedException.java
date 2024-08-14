package com.abiliu.notify.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1572518123690802981L;

    private String message;
}
