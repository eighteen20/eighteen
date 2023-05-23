package cn.ctrlcv.eighteen.wechat.controller.couple.vo;

import lombok.Data;

import java.util.Date;

/**
 * Class Name: CoupleRelationshipVO
 * Class Description: 情侣关系VO
 *
 * @author liujm
 * @date 2023-05-03
 */
@Data
public class CoupleRelationshipVO {

    private Long coupleRelationshipId;
    private Long boyfriendUserId;
    private Long girlfriendUserId;
    private Long loveDate;

}
