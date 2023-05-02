package cn.ctrlcv.eighteen.config;

import cn.ctrlcv.eighteen.annotations.resolver.UserArgumentResolver;
import cn.ctrlcv.eighteen.client.WechatClient;
import cn.ctrlcv.eighteen.interceptor.JwtAuthInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Class Name: WebMvcConfig
 * Class Description:
 *
 * @author liujm
 * @date 2023-04-24
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtAuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/v1/loginRegister/checkLoginCode");
    }

    @Bean
    WebClient webClient(ObjectMapper objectMapper) {
        log.info("注册Http client 客户端：{}", objectMapper.toString());
        return WebClient.builder()
                .baseUrl("https://api.weixin.qq.com")
                .build();
    }
    @SneakyThrows
    @Bean
    WechatClient postClient(WebClient webClient) {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builder(WebClientAdapter.forClient(webClient))
                        .build();
        return httpServiceProxyFactory.createClient(WechatClient.class);
    }

    /**
     * 添加自定义参数解析器
     *
     * @param resolvers 参数解析器列表
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserArgumentResolver());
    }


}
