package com.yagyesh.movie.movieproducer.exceptions;

import java.io.Serial;

public class MovieNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public MovieNotFoundException(String message) {
        super(message);
    }

    public MovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
