package com.hehetenya.phonecontacts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class ErrorResponseDTO {
    private ZonedDateTime timestamp;

    private String status;

    private int statusCode;

    private String message;

    private String path;
}
