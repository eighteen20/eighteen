package cn.ctrlcv.eighteen.config.wx;

import lombok.Data;

/**
 * Class Name: Developer
 * Class Description: 腾讯云-接口调用-开发者配置
 *
 * @author liujm
 * @date 2023-05-03
 */
@Data
public class Developer {

    /**
     * 用户的 SecretId
     */
    private String secretId;

    /**
     * 用户的 SecretKey
     */
    private String secretKey;

    /**
     * 存储桶 bucket 的实际地域
     */
    private String cosRegion;
}
