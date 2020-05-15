package com.skywalter.SkywalterMovieDatabase.exception;

import lombok.Getter;

@Getter
public class ResponseError {

    private String message;

    public ResponseError() {
    }

    public ResponseError(String message) {
        this.message = message;
    }

}
