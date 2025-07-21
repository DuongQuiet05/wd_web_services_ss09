package com.duong.ss09_homeworks.model.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponseDTO<T> {
    private boolean success;
    private T data;
    private Object meta;
    private String message;
    private Object error;

    public ApiResponseDTO(T data, boolean success, Object meta, String message) {
        this.success = success;
        this.data = data;
        this.meta = meta;
        this.message = message;
    }


    public ApiResponseDTO(boolean success, String message, Object error) {
        this.success = success;
        this.message = message;
        this.error = error;
    }

    // Factory method cho response thành công
    public static <T> ApiResponseDTO<T> success(T data, String message) {
        return new ApiResponseDTO<>(data, true, null, message);
    }

    public static <T> ApiResponseDTO<T> success(T data, Object meta, String message) {
        return new ApiResponseDTO<>(data, true, meta, message);
    }

    // Factory method cho response thất bại
    public static <T> ApiResponseDTO<T> fail(String message, Object error) {
        return new ApiResponseDTO<>(false, message, error);
    }
}
