package com.oh.apiclients.domain.exception;

public class NotFoundException extends RuntimeException {

    private static final String DESCRIPTION = "Bad Request Exception (404)";

    public NotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}