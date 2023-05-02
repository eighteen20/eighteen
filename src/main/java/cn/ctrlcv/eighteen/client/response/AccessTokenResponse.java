package cn.ctrlcv.eighteen.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class Name: AccessTokenDTO
 * Class Description: 获取微信AccessToken接口的响应结果
 *
 * @author liujm
 * @date 2023-04-30
 */
@Data
public class AccessTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private Long expiresIn;
}
