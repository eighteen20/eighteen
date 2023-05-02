package cn.ctrlcv.eighteen.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * annotation Name: SkipTokenCheck
 * annotation Description: 跳过特定请求方法的 token 校验
 *
 * @author liujm
 * @date 2023-04-25
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface SkipTokenCheck {
}
