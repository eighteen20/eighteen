package cn.ctrlcv.eighteen.wechat.mapper.entity;

import cn.ctrlcv.eighteen.common.model.BaseEntityField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 情侣关系表
 * @author ljm19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "couple_relationship")
public class CoupleRelationshipEntity extends BaseEntityField implements Serializable {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 男朋友用户ID
     */
    @TableField(value = "boyfriend_user_id")
    private Long boyfriendUserId;

    /**
     * 女朋友用户ID
     */
    @TableField(value = "girlfriend_user_id")
    private Long girlfriendUserId;

    /**
     * 恋爱日期
     */
    @TableField(value = "love_date")
    private Date loveDate;

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_BOYFRIEND_USER_ID = "boyfriend_user_id";

    public static final String COL_GIRLFRIEND_USER_ID = "girlfriend_user_id";

    public static final String COL_LOVE_DATE = "love_date";
}