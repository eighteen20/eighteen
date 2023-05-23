package cn.ctrlcv.eighteen.wechat.controller.other;

import cn.ctrlcv.eighteen.annotations.SkipTokenCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Class Name: WechatController
 * Class Description: 微信服务器严重
 *
 * @author liujm
 * @date 2023-04-27
 */
@Slf4j
@RestController
public class WechatController {


    @SkipTokenCheck
    @GetMapping(value = "/wx")
    public Long validate(@RequestParam("signature") String signature,
                         @RequestParam("timestamp") Long timestamp,
                         @RequestParam("nonce") String nonce,
                         @RequestParam("echostr") Long echostr) {
        log.info("微信验证开始--------");

        try {
            //这里的“token”是正确的token，由服务器定义，小程序只有使用正确的token，微信服务器才会验证通过
            String checkSignature = this.sha1(timestamp, nonce);
            if (checkSignature.equals(signature)) {
                return echostr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return echostr  ;

    }

    private String sha1(Long timestamp, String nonce) {
        try {
            Object[] array = new Object[]{"0123icdkcicnakc12c5aac4a8c48as4s", timestamp, nonce};
            StringBuilder sb = new StringBuilder();
            // 字符串排序
            Arrays.sort(array);
            for (Object o : array) {
                sb.append(o);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuilder hexStr = new StringBuilder();
            String shaHex;
            for (byte b : digest) {
                shaHex = Integer.toHexString(b & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("11111111");
        }
    }
}
