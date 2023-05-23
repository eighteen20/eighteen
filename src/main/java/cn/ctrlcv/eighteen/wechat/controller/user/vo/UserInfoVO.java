package cn.ctrlcv.eighteen.wechat.controller.user.vo;

import lombok.Data;

import java.util.Date;

/**
 * Class Name: UserInfoVO
 * Class Description: 用户信息
 *
 * @author liujm
 * @date 2023-05-03
 */
@Data
public class UserInfoVO {

    private Integer userId;
    private String phoneNumber;
    private String nickname;
    private Date birthDate;
    private Date lunarBirthday;
    private String gender;
    private String avatar;
}
