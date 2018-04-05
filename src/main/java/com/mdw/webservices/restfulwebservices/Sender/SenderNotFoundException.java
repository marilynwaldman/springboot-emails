package com.mdw.webservices.restfulwebservices.Sender;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)

public class SenderNotFoundException  extends RuntimeException {

    public SenderNotFoundException(String message) {
        super(message);
    }

}
