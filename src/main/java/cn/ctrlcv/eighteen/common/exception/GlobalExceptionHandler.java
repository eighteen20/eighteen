package cn.ctrlcv.eighteen.common.exception;

import cn.ctrlcv.eighteen.common.enums.ApiErrorEnum;
import cn.ctrlcv.eighteen.common.model.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * Class Name: GlobalExceptionHandler
 * Class Description: 全局异常处理
 *
 * @author liujm
 * @date 2023-04-25
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ApiResult<?> handleGeneralException(Exception ex, WebRequest request) {
        log.error("拦截到系统异常：{}", ex.getMessage());
        ex.printStackTrace();
        return ApiResult.error(ApiErrorEnum.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ApiResult<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        log.error("非法参数异常：{}", ex.getMessage());
        ex.printStackTrace();
        return ApiResult.error(ApiErrorEnum.BAD_REQUEST, ex.getMessage());
    }


}
