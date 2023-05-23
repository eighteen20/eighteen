package cn.ctrlcv.eighteen.common.enums;

/**
 * enum Name: ApiErrorEnum
 * enum Description: API错误枚举
 *
 * @author liujm
 * @date 2023-04-24
 */
public enum ApiErrorEnum {

    /**
     * 请求成功
     */
    SUCCESS(200, "success"),

    /**
     * 请求失败:
     */
    BAD_REQUEST(400, "Bad Request"),

    /**
     * 未授权
     */
    UNAUTHORIZED(401, "未授权的访问"),

    /**
     * 禁止访问
     */
    FORBIDDEN(403, "Forbidden"),

    /**
     * 未找到
     */
    NOT_FOUND(404, "Not Found"),

    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    LOGIN_EXPIRED(1001, "登录过期，请重新登录"),

    /**
     * 未找到用户信息
     */
    USER_NOT_FOUND(1002, "未找到用户信息"),

    /**
     * 未能获得 OPENID
     */
    FAILED_TO_OBTAIN_OPENID(2000, "微信身份认证失败，请重试"),

    FAILED_TO_SEND_SUBSCRIPTION_MESSAGE(2001, "微信小程序订阅消息发送失败"),

    FAILED_TO_OBTAIN_ACCESS_TOKEN(2002, "微信AccessToken获取失败，请重试"),

    ;

    private final int code;
    private final String message;

    ApiErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
