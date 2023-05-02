package cn.ctrlcv.eighteen.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Class Name: Code2SessionDTO
 * Class Description: 登录凭证校验返回对象
 *
 * @author liujm
 * @date 2023-04-24
 */

@Data
public class Code2SessionResponse {

    /**
     * 	会话密钥
     */
    @JsonProperty("session_key")
    private String sessionKey;

    /**
     * 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回
     */
    private String unionid;

    /**
     * 错误信息
     */
    private String errmsg;

    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 错误码
     */
    private Integer errcode;
}
