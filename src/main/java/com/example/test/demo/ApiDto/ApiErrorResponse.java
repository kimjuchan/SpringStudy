package com.example.test.demo.ApiDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {

    private HttpStatus status;
    private String errorMsg;

    public ApiErrorResponse(HttpStatus status) {
        this.status = status;
    }
}
