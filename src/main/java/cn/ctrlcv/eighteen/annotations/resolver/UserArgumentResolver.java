package cn.ctrlcv.eighteen.annotations.resolver;

import cn.ctrlcv.eighteen.annotations.CurrentUser;
import cn.ctrlcv.eighteen.common.constant.SysConstant;
import cn.ctrlcv.eighteen.common.enums.ApiErrorEnum;
import cn.ctrlcv.eighteen.common.exception.CustomException;
import cn.ctrlcv.eighteen.common.model.UserToken;
import cn.ctrlcv.eighteen.common.utils.JwtUtil;
import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Class Name: UserArgumentResolver
 * Class Description: 解析 @{@link CurrentUser} 注解
 *
 * @author liujm
 * @date 2023-04-26
 */
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public UserToken resolveArgument(@NonNull MethodParameter parameter,
                                     ModelAndViewContainer mavContainer,
                                     NativeWebRequest webRequest,
                                     WebDataBinderFactory binderFactory) {
        // 解析 JWT Token，然后返回用户对象。

        String token = webRequest.getHeader(SysConstant.AUTHORIZATION);
        if (token == null) {
            // 可以根据需要抛出异常或者返回null
            throw new CustomException(ApiErrorEnum.UNAUTHORIZED);
        }
        token = token.replace(SysConstant.BEARER, "");
        boolean b = JwtUtil.validateToken(token);
        if (!b) {
            throw new CustomException(ApiErrorEnum.LOGIN_EXPIRED);
        }
        return JwtUtil.getUserFromToken(token);
    }

}
