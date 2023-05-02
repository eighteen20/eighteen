package cn.ctrlcv.eighteen.client;

import cn.ctrlcv.eighteen.client.request.SendSubscribeMessageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

/**
 * Class Name: WechatClient
 * Class Description: 微信公众平台请求客户端
 *
 * @author liujm
 * @date 2023-04-24
 */
@HttpExchange(accept = "application/json", contentType = "application/json")
public interface WechatClient {

    /**
     * 登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
     *
     * @param appid 小程序 appId
     * @param secret 小程序 appSecret
     * @param jsCode 登录时获取的 code，可通过wx.login获取
     * @param grantType 授权类型，此处只需填写 authorization_code
     * @return {
     * "openid":"123456",
     * "session_key":"key",
     * "unionid":"unionid",
     * "errcode":0,
     * "errmsg":"ABC"
     * }
     */
    @GetExchange("/sns/jscode2session")
    Mono<String> code2Session(@RequestParam("appid") String appid, @RequestParam("secret") String secret,
                                       @RequestParam("js_code") String jsCode,
                              @RequestParam("grant_type") String grantType);


    /**
     * 获取小程序全局唯一后台接口调用凭据，token有效期为7200s，
     *
     * @param grantType 固定值 client_credential
     * @param appid 小程序唯一凭证
     * @param secret 小程序唯一凭证密钥
     * @return { "access_token":"访问token", "expires_in":7200 }
     */
    @GetExchange("/cgi-bin/token")
    Mono<String> getAccessToken(@RequestParam("grant_type") String grantType,
                                @RequestParam("appid") String appid,
                                @RequestParam("secret") String secret);


    /**
     * 发送订阅消息
     *
     * @param accessToken 接口调用凭证，该参数为 URL 参数，非 Body 参数。
     * @param request {@link SendSubscribeMessageRequest}
     * @return { "errcode":0, "errmsg":"ok" }
     */
    @PostExchange("/cgi-bin/message/subscribe/send")
    Mono<String> sendSubscribeMessage(@RequestParam("access_token") String accessToken, @RequestBody SendSubscribeMessageRequest request);

}
