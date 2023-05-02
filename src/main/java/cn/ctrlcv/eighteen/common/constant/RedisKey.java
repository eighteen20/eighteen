package cn.ctrlcv.eighteen.common.constant;

/**
 * Class Name: RedisKey
 * Class Description: redis key
 *
 * @author liujm
 * @date 2023-04-30
 */
public class RedisKey {

    public static class WeChat {
        private static final String WECHAT_PREFIX = "wechat:";

        public static final String ACCESS_TOKEN_KEY = WECHAT_PREFIX + "access_token";
    }
}
