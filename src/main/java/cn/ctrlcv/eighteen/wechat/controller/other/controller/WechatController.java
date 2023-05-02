package cn.ctrlcv.eighteen.wechat.controller.other.controller;

import cn.ctrlcv.eighteen.annotations.SkipTokenCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Class Name: WechatController
 * Class Description: TODO
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
                         @RequestParam("echostr") Long echostr) throws NoSuchAlgorithmException {
        log.info("微信验证开始--------");

        try {
            //这里的“token”是正确的token，由服务器定义，小程序只有使用正确的token，微信服务器才会验证通过
            String checkSignature = this.sha1("0123icdkcicnakc12c5aac4a8c48as4s", timestamp, nonce);
            if (checkSignature.equals(signature)) {
                return echostr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return echostr  ;

    }

    private String sha1(String token, Long timestamp, String nonce) throws NoSuchAlgorithmException {
        try {
            Object[] array = new Object[]{token, timestamp, nonce};
            StringBuffer sb = new StringBuffer();
            // 字符串排序
            Arrays.sort(array);
            for (int i = 0; i < array.length; i++) {
                sb.append(array[i]);
            }
            String str = sb.toString();
            // SHA1签名生成
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(str.getBytes());
            byte[] digest = md.digest();

            StringBuffer hexstr = new StringBuffer();
            String shaHex = "";
            for (int i = 0; i < digest.length; i++) {
                shaHex = Integer.toHexString(digest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexstr.append(0);
                }
                hexstr.append(shaHex);
            }
            return hexstr.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("11111111");
        }
    }
}
