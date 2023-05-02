package cn.ctrlcv.eighteen.common.model;

import lombok.Data;

/**
 * Class Name: UserToken
 * Class Description: 这个类将被用于生成用户token
 *
 * @author liujm
 * @date 2023-04-25
 */
@Data
public class UserToken {
    private Integer userId;
    private String openId;
    private String nickname;
}
