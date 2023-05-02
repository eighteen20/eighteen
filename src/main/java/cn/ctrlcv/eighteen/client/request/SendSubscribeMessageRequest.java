package cn.ctrlcv.eighteen.client.request;

import cn.ctrlcv.eighteen.common.constant.SysConstant;
import cn.ctrlcv.eighteen.common.utils.DateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * Class Name: SendSubscribeMessageRequest
 * Class Description: 发送订阅消息请求对象
 *
 * @author liujm
 * @date 2023-05-01
 */
@ToString
@Getter
public class SendSubscribeMessageRequest {


    /**
     * 接收者（用户）的 openid
     */
    @Setter
    private String touser;

    /**
     * 所需下发的订阅模板id
     */
    @Setter
    @JsonProperty("template_id")
    private String templateId;

    /**
     * 点击模板卡片后的跳转页面，仅限本小程序内的页面
     */
    @Setter
    private String page = "pages/chan_shi/chan_shi";

    /**
     * 模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }
     * <p>
     *    <a href="https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/mp-message-management/subscribe-message/sendMessage.html#%E8%AE%A2%E9%98%85%E6%B6%88%E6%81%AF%E5%8F%82%E6%95%B0%E5%80%BC%E5%86%85%E5%AE%B9%E9%99%90%E5%88%B6%E8%AF%B4%E6%98%8E"> 订阅消息参数值内容限制说明</a>
     * </p>
     * <p>
     *     消息内容：有些数据需要实时的。例如日期，所以表里只存了占位符。这里需要替换成实时的数据。
     *     <ul>
     *         <ol>时间占位符：yyyy年MM月dd日</ol>
     *     </ul>
     * </p>
     */
    private Map<?, ?> data;

    /**
     * 跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
     */
    @Setter
    @JsonProperty("miniprogram_state")
    private String miniprogramState = "formal";

    /**
     * 进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)
     */
    @Setter
    private String lang = "zh_CN";

    public void setData(String data) {
        // 替换时间
        data = data.replace(SysConstant.SEND_SUBSCRIBE_MESSAGE_PLACEHOLDER,
                DateUtil.getCurrentDateString(SysConstant.SEND_SUBSCRIBE_MESSAGE_PLACEHOLDER));

        ObjectMapper objectMapper = new ObjectMapper();
        Map<?, ?> map;
        try {
            map = objectMapper.readValue(data, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        this.data = map;
    }
}
