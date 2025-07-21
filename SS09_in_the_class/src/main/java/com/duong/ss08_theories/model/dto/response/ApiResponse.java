package com.duong.ss08_theories.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse<T> {
    private boolean success;
    private T data;
    private Object meta;
    private String message;
    private Object error;

    // Constructor dùng cho response thành công
    public ApiResponse(T data, boolean success, Object meta, String message) {
        this.data = data;
        this.success = success;
        this.meta = meta;
        this.message = message;
    }

    // Constructor dùng cho response thất bại
    public ApiResponse(boolean success, String message, Object error) {
        this.success = success;
        this.message = message;
        this.error = error;
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, true, null, message);
    }

    public static <T> ApiResponse<T> fail(String message, Object error) {
        return new ApiResponse<>(false, message, error);
    }
}
