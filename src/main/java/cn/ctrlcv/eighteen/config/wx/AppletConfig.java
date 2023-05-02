package cn.ctrlcv.eighteen.config.wx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Class Name: AppletConfig
 * Class Description: 微信开发相关配置和参数
 *
 * @author liujm
 * @date 2023-04-24
 */
@Data
@Component
@ConfigurationProperties(prefix = "wx")
public class AppletConfig {
    private WechatApplet applet;
}
