package com.dani.appudemyspring.exceptions;

import java.io.Serial;

public class UserServiceException extends  RuntimeException{

    @Serial
    private static final long serialVersionUID = -9137740690346528369L;

    public UserServiceException(String message) {
        super(message);
    }
}
