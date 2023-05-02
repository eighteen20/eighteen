package cn.ctrlcv.eighteen.client.service;

import cn.ctrlcv.eighteen.common.constant.RedisKey;
import cn.ctrlcv.eighteen.common.enums.ApiErrorEnum;
import cn.ctrlcv.eighteen.common.exception.CustomException;
import cn.ctrlcv.eighteen.client.WechatClient;
import cn.ctrlcv.eighteen.client.response.AccessTokenResponse;
import cn.ctrlcv.eighteen.config.wx.AppletConfig;
import cn.ctrlcv.eighteen.event.AccessTokenRefreshedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * Class Name: WechatAccessTokenService
 * Class Description:
 *
 * @author liujm
 * @date 2023-04-30
 */
@Slf4j
@Service
public class WechatAccessTokenService implements ApplicationEventPublisherAware {

    @Resource
    private AppletConfig appletConfig;
    @Resource
    private WechatClient wechatClient;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private ApplicationEventPublisher applicationEventPublisher;


    /**
     * 刷新token
     */
    public void refreshAccessToken() {
        wechatClient.getAccessToken("client_credential", appletConfig.getApplet().getAppid(),
                appletConfig.getApplet().getAppSecret()).subscribe(data -> {
            log.info("获取新的微信AccessToken结果: {}", data);
            ObjectMapper objectMapper = new ObjectMapper();
            AccessTokenResponse accessTokenDTO;
            try {
                accessTokenDTO = objectMapper.readValue(data, AccessTokenResponse.class);
            } catch (JsonProcessingException e) {
                throw new CustomException(ApiErrorEnum.FAILED_TO_OBTAIN_ACCESS_TOKEN);
            }
            storeAccessToken(accessTokenDTO);
        });
    }

    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 缓存token
     *
     * @param dto {@link AccessTokenResponse}
     */
    private void storeAccessToken(AccessTokenResponse dto) {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        valueOps.set(RedisKey.WeChat.ACCESS_TOKEN_KEY, dto.getAccessToken(), Duration.ofSeconds(dto.getExpiresIn()));

        // 触发 AccessTokenRefreshedEvent 事件
        applicationEventPublisher.publishEvent(new AccessTokenRefreshedEvent(this));
    }

    /**
     * 获取token
     *
     * @return token
     */
    public String getAccessToken() {
        ValueOperations<String, String> valueOps = stringRedisTemplate.opsForValue();
        return valueOps.get(RedisKey.WeChat.ACCESS_TOKEN_KEY);
    }

}
