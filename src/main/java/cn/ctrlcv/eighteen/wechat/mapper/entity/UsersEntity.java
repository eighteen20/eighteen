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
 * @author ljm19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "users")
public class UsersEntity extends BaseEntityField implements Serializable  {
    /**
     * 用户ID，自动递增
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Integer userId;

    /**
     * 用户的openId
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 手机号
     */
    @TableField(value = "phone_number")
    private String phoneNumber;

    /**
     * 姓名
     */
    @TableField(value = "full_name")
    private String fullName;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 生日
     */
    @TableField(value = "birth_date")
    private Date birthDate;

    /**
     * 农历生日
     */
    @TableField(value = "lunar_birthday")
    private Date lunarBirthday;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;


    @Serial
    private static final long serialVersionUID = 1L;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_OPEN_ID = "open_id";

    public static final String COL_PHONE_NUMBER = "phone_number";

    public static final String COL_FULL_NAME = "full_name";

    public static final String COL_NICKNAME = "nickname";

    public static final String COL_BIRTH_DATE = "birth_date";

    public static final String COL_GENDER = "gender";


}