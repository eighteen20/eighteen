package cn.ctrlcv.eighteen.common.exception;

import cn.ctrlcv.eighteen.common.enums.ApiErrorEnum;

/**
 * Class Name: CustomException
 * Class Description: 自定义异常
 *
 * @author liujm
 * @date 2023-04-25
 */
@SuppressWarnings("unused")
public class CustomException extends RuntimeException {

    private final int code;

    private final String error;


    public CustomException(int code, String message) {
        super(message);
        this.code = code;
        this.error = message;
    }

    public CustomException(ApiErrorEnum exceptionEnum) {
        super(String.valueOf(exceptionEnum.getCode()));
        this.code   = exceptionEnum.getCode();
        this.error  = exceptionEnum.getMessage();
    }

    public String getError() {
        return error;
    }


    /**
     *  avoid the expensive and useless stack trace for api exceptions
     *  @see Throwable#fillInStackTrace()
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
