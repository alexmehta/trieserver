package com.trie.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MalformedWordException extends RuntimeException {
    public MalformedWordException() {
        super();
    }

    public MalformedWordException(String message) {
        super(message);
    }


}
