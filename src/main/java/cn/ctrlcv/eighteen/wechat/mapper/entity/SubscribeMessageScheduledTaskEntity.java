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
 * @author ljm19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "subscribe_message_scheduled_task")
public class SubscribeMessageScheduledTaskEntity extends BaseEntityField implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    /**
     * 接收人openID
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 订阅消息模板ID
     */
    @TableField(value = "template_id")
    private String templateId;

    /**
     * 订阅消息内容(JSON字符串)
     */
    @TableField(value = "message_content")
    private String messageContent;

    /**
     * 定时任务执行时间(cron表达式)
     */
    @TableField(value = "cron_expression")
    private String cronExpression;

    /**
     * 任务名称
     */
    @TableField(value = "task_name")
    private String taskName;

    /**
     * 任务类型（执行对应的代码函数）
     */
    @TableField(value = "task_type")
    private Integer taskType;

    /**
     * 是否项目启动后立即执行
     */
    @TableField(value = "\"execute_after_startup \"")
    private Boolean executeAfterStartup;

    @Serial
    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_OPEN_ID = "open_id";

    public static final String COL_TEMPLATE_ID = "template_id";

    public static final String COL_MESSAGE_CONTENT = "message_content";

    public static final String COL_CRON_EXPRESSION = "cron_expression";

    public static final String COL_TASK_NAME = "task_name";

    public static final String COL_TASK_TYPE = "task_type";

    public static final String COL_EXECUTE_AFTER_STARTUP = "execute_after_startup ";
}