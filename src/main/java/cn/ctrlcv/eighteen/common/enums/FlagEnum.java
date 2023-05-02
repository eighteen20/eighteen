package cn.ctrlcv.eighteen.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * enum Name: FlagEnum
 * enum Description: 数据逻辑删除标志
 *
 * @author liujm
 * @date 2023-04-23
 */
public enum FlagEnum {

    /**
     * 有效
     */
    VALID(true, "有效"),

    /**
     * 无效
     */
    INVALID(false, "无效");

    @EnumValue
    private Boolean code;
    private String desc;

    FlagEnum(Boolean code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Boolean getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
