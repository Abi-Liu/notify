package com.abiliu.notify.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BadRequestException extends RuntimeException {
    private static final long serialVersionUID = 983347026031334947L;
    private String message;
}
