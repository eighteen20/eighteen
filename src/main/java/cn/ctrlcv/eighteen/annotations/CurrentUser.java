package cn.ctrlcv.eighteen.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * annotation Name: CurrentUser
 * annotation Description: 获取请求的token中的用户信息
 *
 * @author liujm
 * @date 2023-04-26
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
