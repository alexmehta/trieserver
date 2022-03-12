package com.trie.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class WordNotFoundException extends RuntimeException {
    public WordNotFoundException() {
        super();
    }

    public WordNotFoundException(String message) {
        super(message);
    }
}
