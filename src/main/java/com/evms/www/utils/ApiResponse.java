package com.evms.www.utils;

import org.springframework.http.ResponseEntity;

public class ApiResponse <T> {
    public boolean success;
    public String message;
    public T data;

    public ApiResponse (boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

//    public static ResponseEntity<ApiResponse<T>> ok (String message, T data) {
//        return ResponseEntity.ok(new ApiResponse<>(true, message, data));
//    }
}
