package com.trie.server.exceptions;



public class HeadlessTreeException extends RuntimeException {

    public HeadlessTreeException() {
        super();
    }

    public HeadlessTreeException(String message) {
        super(message);

    }
}
