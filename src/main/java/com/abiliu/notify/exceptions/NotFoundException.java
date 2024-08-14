package com.abiliu.notify.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class NotFoundException extends RuntimeException {
    private final static long serialVersionUID = -2356794321846600534L;

    private String message;
}
