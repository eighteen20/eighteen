package cn.ctrlcv.eighteen.scheduled.impl;

import cn.ctrlcv.eighteen.client.WechatClient;
import cn.ctrlcv.eighteen.client.request.SendSubscribeMessageRequest;
import cn.ctrlcv.eighteen.client.service.WechatAccessTokenService;
import cn.ctrlcv.eighteen.wechat.mapper.entity.SubscribeMessageScheduledTaskEntity;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Class Name: subscribeMessageTask
 * Class Description: 微信小程序订阅消息发送任务
 *
 * @author liujm
 * @date 2023-05-02
 */
@Slf4j
@Component("subscribeMessageTask")
public class SubscribeMessageTask implements TaskExecutor {

    @Resource
    private WechatClient wechatClient;
    @Resource
    private WechatAccessTokenService accessTokenService;

    @Override
    public void execute(SubscribeMessageScheduledTaskEntity task) {

        log.info("执行订阅消息发送任务: {}", task);

        String accessToken = accessTokenService.getAccessToken();
        SendSubscribeMessageRequest request = new SendSubscribeMessageRequest();
        request.setTemplateId(task.getTemplateId());
        request.setData(task.getMessageContent());
        request.setTouser(task.getOpenId());
        request.setMiniprogramState("trial");
        log.info("订阅消息发送请求: {}", request);
        wechatClient.sendSubscribeMessage(accessToken, request)
                .subscribe(res -> log.info("任务ID：{} ==== 订阅消息发送结果: {}", task.getId(), res));
    }

}
