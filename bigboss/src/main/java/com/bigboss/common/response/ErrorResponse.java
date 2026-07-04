package com.bigboss.common.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse{

    private int status;

    private String error;

    private String message;

    private LocalDateTime timestamp;

    private String path;

}