package cn.ctrlcv.eighteen.scheduled;

import cn.ctrlcv.eighteen.client.service.WechatAccessTokenService;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Class Name: MainScheduled
 * Class Description: 所有定时任务入口
 *
 * @author liujm
 * @date 2023-04-30
 */
@Component
public class MainScheduled {

    @Resource
    private WechatAccessTokenService wechatAccessTokenService;


    /**
     * 每100分钟获取一次token
     */
    @Scheduled(fixedRate = 100 * 60 * 1000, initialDelay = 5000)
    public void refreshAccessToken() {
        wechatAccessTokenService.refreshAccessToken();
    }
}
