package net.etg.backendchallenge.exception;

import lombok.Data;

@Data
public class ErrorDetail {

    private String code;
    private String path;
    private String message;
}
