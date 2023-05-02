package cn.ctrlcv.eighteen.wechat.mapper.entity;

import cn.ctrlcv.eighteen.common.model.BaseEntityField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 任务类型（对应代码中实现类）
 *
 * @author ljm19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "task_type_info")
public class TaskTypeInfoEntity extends BaseEntityField implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 任务类型的名称
     */
    @TableField(value = "task_type_name")
    private String taskTypeName;

    /**
     * 任务执行类的Bean名称（即@Component注解中的名称）
     */
    @TableField(value = "task_executor_bean_name")
    private String taskExecutorBeanName;

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TASK_TYPE_NAME = "task_type_name";

    public static final String COL_TASK_EXECUTOR_BEAN_NAME = "task_executor_bean_name";
}