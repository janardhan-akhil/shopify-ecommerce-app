package com.example.utils;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse<T> {

    public String message;
    public boolean status;
    public T data;
    public LocalDateTime  timestamp;


    public static <T> ApiResponse<T> success(String message, T data) {

        return ApiResponse.<T>builder()
        .message(message)
                .data(data)
                .status(true)
                .timestamp(LocalDateTime.now())
                .build();
    }

    public static <T> ApiResponse<T> success(T data) {
        return success("Operation Successfull", data);
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .message(message)
                .status(false)
                .timestamp(LocalDateTime.now())
                .build();
    }
}