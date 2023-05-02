package cn.ctrlcv.eighteen.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;

/**
 * Class Name: AccessTokenRefreshedEvent
 * Class Description: token刷新事件
 *
 * @author liujm
 * @date 2023-05-02
 */
@Slf4j
public class AccessTokenRefreshedEvent extends ApplicationEvent {

    public AccessTokenRefreshedEvent(Object source) {
        super(source);
        log.info("监听：微信访问令牌刷新事件");
    }
}
