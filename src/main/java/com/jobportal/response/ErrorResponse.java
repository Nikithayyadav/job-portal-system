package com.jobportal.response;

import lombok.AllArgsConstructor; 
import lombok.Data;

@Data 
@AllArgsConstructor
public class ErrorResponse {

    private String message;
}
