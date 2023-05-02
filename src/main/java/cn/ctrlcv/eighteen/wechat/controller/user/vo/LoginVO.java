package cn.ctrlcv.eighteen.wechat.controller.user.vo;

import lombok.Data;

/**
 * Class Name: LoginVO
 * Class Description: 登录后返回的VO
 *
 * @author liujm
 * @date 2023-04-24
 */
@Data
public class LoginVO {

    /**
     * 是否是新用户
     */
    private Boolean isNewUser;

    private String token;
}
