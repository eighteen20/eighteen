package cn.ctrlcv.eighteen.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * enum Name: GenderEnum
 * enum Description: 性别枚举
 *
 * @author liujm
 * @date 2023-05-03
 */
public enum GenderEnum {

    /**
     * 男
     */
    MAN("1", "男"),

    /**
     * 女
     */
    WOMAN("0", "女");

    @EnumValue
    @Getter
    private final String code;

    @Getter
    private final String desc;

    GenderEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
