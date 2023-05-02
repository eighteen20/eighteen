package cn.ctrlcv.eighteen.common.model;

import cn.ctrlcv.eighteen.common.enums.ApiErrorEnum;

/**
 * Class Name: ApiResult
 * Class Description: 统一API响应结果封装
 *
 * @author liujm
 * @date 2023-04-24
 */
@SuppressWarnings("unused")
public class ApiResult<T> {

    private int code;
    private String message;
    private T data;

    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult<>(ApiErrorEnum.SUCCESS.getCode(), "success", data);
    }

    public static <T> ApiResult<T> success(String message, T data) {
        return new ApiResult<>(ApiErrorEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> ApiResult<T> error(ApiErrorEnum errorEnum, String message) {
        return new ApiResult<>(errorEnum.getCode(), message, null);
    }

    public static <T> ApiResult<T> error(ApiErrorEnum errorEnum) {
        return new ApiResult<>(errorEnum.getCode(), errorEnum.getMessage(), null);
    }

    public static <T> ApiResult<T> badRequest(String message) {
        return new ApiResult<>(ApiErrorEnum.BAD_REQUEST.getCode(), message, null);
    }

    public static <T> ApiResult<T> unauthorized(String message) {
        return new ApiResult<>(ApiErrorEnum.UNAUTHORIZED.getCode(), message, null);
    }

    public static <T> ApiResult<T> forbidden(String message) {
        return new ApiResult<>(ApiErrorEnum.FORBIDDEN.getCode(), message, null);
    }

    public static <T> ApiResult<T> notFound(String message) {
        return new ApiResult<>(ApiErrorEnum.NOT_FOUND.getCode(), message, null);
    }

    public static <T> ApiResult<T> internalServerError(String message) {
        return new ApiResult<>(ApiErrorEnum.INTERNAL_SERVER_ERROR.getCode(), message, null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
