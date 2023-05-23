package cn.ctrlcv.eighteen.config;

import cn.ctrlcv.eighteen.config.wx.AppletConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class Name: CloudTencentConfig
 * Class Description: 腾讯云配置
 *
 * @author liujm
 * @date 2023-05-03
 */
@Slf4j
@Configuration
public class CloudTencentConfig {

    @Resource
    private AppletConfig appletConfig;

    /**
     * 初始化 腾讯云-存储对象客户端
     *
     * @return {@link COSClient}
     */
    @Bean
    public COSClient cosClient() {
        String secretId = appletConfig.getDeveloper().getSecretId();
        String secretKey = appletConfig.getDeveloper().getSecretKey();
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region region = new Region(appletConfig.getDeveloper().getCosRegion());
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 生成 cos 客户端。
        log.info("初始化腾讯云 COS服务客户端=========");
        return new COSClient(cred, clientConfig);
    }
}
