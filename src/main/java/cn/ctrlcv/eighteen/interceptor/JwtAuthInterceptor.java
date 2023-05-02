package cn.ctrlcv.eighteen.interceptor;

import cn.ctrlcv.eighteen.annotations.SkipTokenCheck;
import cn.ctrlcv.eighteen.common.constant.SysConstant;
import cn.ctrlcv.eighteen.common.enums.ApiErrorEnum;
import cn.ctrlcv.eighteen.common.model.ApiResult;
import cn.ctrlcv.eighteen.common.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.lang.reflect.Method;


/**
 * Class Name: JwtAuthInterceptor
 * Class Description:
 *
 * @author liujm
 * @date 2023-04-24
 */
public class JwtAuthInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper = new ObjectMapper();

        private void writeUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        ApiResult<?> apiResult = ApiResult.error(ApiErrorEnum.UNAUTHORIZED, message);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(apiResult));
    }


    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {

        if (handler instanceof HandlerMethod handlerMethod) {
            Method method = handlerMethod.getMethod();
            if (method.isAnnotationPresent(SkipTokenCheck.class) || handlerMethod.getBeanType().isAnnotationPresent(SkipTokenCheck.class)) {
                return true;
            }
        }


        String authHeader = request.getHeader(SysConstant.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(SysConstant.BEARER)) {
            writeUnauthorizedResponse(response, "Missing or invalid Authorization header");
            return false;
        }

        String token = authHeader.substring(7);

        if (!JwtUtil.validateToken(token)) {
            writeUnauthorizedResponse(response, "Invalid token");
            return false;
        }

        return true;
    }

}
