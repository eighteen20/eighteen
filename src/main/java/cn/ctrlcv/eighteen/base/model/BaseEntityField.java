package cn.ctrlcv.eighteen.base.model;

import cn.ctrlcv.eighteen.base.enums.FlagEnum;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * Class Name: BaseEntityField
 * Class Description: 基础实体类字段
 *
 * @author liujm
 * @date 2023-04-23
 */
@Data
public class BaseEntityField {

    /**
     * 是否有效，默认为TRUE
     */
    @TableField(value = "flag")
    private FlagEnum flag;

    /**
     * 创建日期，自动使用当前时间
     */
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Date createdAt;

    /**
     * 创建人
     */
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 修改日期，自动使用当前时间
     */
    @TableField(value = "updated_at", fill = FieldFill.UPDATE)
    private Date updatedAt;

    /**
     * 修改人
     */
    @TableField(value = "updated_by", fill = FieldFill.UPDATE)
    private String updatedBy;

    public static final String COL_FLAG = "flag";

    public static final String COL_CREATED_AT = "created_at";

    public static final String COL_CREATED_BY = "created_by";

    public static final String COL_UPDATED_AT = "updated_at";

    public static final String COL_UPDATED_BY = "updated_by";

}
