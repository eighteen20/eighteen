package cn.ctrlcv.eighteen.wechat.controller.user.dto;

import lombok.Data;

/**
 * Class Name: RegisterDTO
 * Class Description: 注册(补充资料)参数
 *
 * @author liujm
 * @date 2023-04-27
 */
@Data
public class RegisterDTO {

    private Integer userId;

    private String openId;

    private String birthdayType;

    private String birthday;

    private String nickname;

    private String phoneNumber;

    private String gender;

    private String fullName;

}
