package cn.ctrlcv.eighteen.scheduled;

import cn.ctrlcv.eighteen.event.AccessTokenRefreshedEvent;
import cn.ctrlcv.eighteen.scheduled.impl.TaskExecutor;
import cn.ctrlcv.eighteen.wechat.mapper.SubscribeMessageScheduledTaskEntityMapper;
import cn.ctrlcv.eighteen.wechat.mapper.TaskTypeInfoEntityMapper;
import cn.ctrlcv.eighteen.wechat.mapper.entity.SubscribeMessageScheduledTaskEntity;
import cn.ctrlcv.eighteen.wechat.mapper.entity.TaskTypeInfoEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * Class Name: DynamicTaskScheduler
 * Class Description: 动态定时任务
 *
 * @author liujm
 * @date 2023-05-01
 */
@Slf4j
@Component
public class DynamicTaskScheduler implements ApplicationListener<AccessTokenRefreshedEvent> {

    @Resource
    private TaskScheduler taskScheduler;
    @Resource
    private SubscribeMessageScheduledTaskEntityMapper scheduledTaskMapper;
    @Resource
    private TaskTypeInfoEntityMapper taskTypeInfoMapper;
    @Resource
    private ApplicationContext applicationContext;

    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    @Override
    public void onApplicationEvent(@NonNull AccessTokenRefreshedEvent event) {
        init();
    }


    /**
     * 初始化所有定时任务
     */
    public void init() {

        QueryWrapper<SubscribeMessageScheduledTaskEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SubscribeMessageScheduledTaskEntity.COL_FLAG, true);
        List<SubscribeMessageScheduledTaskEntity> tasks = scheduledTaskMapper.selectList(queryWrapper);
        log.info("获取所有启用的定时任务: {}", tasks);
        tasks.forEach(this::scheduleTask);
    }

    public void scheduleTask(SubscribeMessageScheduledTaskEntity task) {
        CronTrigger trigger = new CronTrigger(task.getCronExpression());
        ScheduledFuture<?> future = taskScheduler.schedule(() -> executeTask(task), trigger);
        scheduledTasks.put(task.getId().toString(), future);
        log.info("Task '{}' scheduled with cron expression '{}'", task.getId(), task.getCronExpression());


        if (task.getExecuteAfterStartup()) {
            extracted(task);
        }
    }

    /**
     * 重新调度已存在的任务。它首先取消现有的任务，然后重新调度新任务。
     *
     * @param taskId 任务ID
     */
    @SuppressWarnings("unused")
    public void rescheduleTask(Integer taskId) {
        SubscribeMessageScheduledTaskEntity task = scheduledTaskMapper.selectById(taskId);
        if (task != null) {
            cancelTask(task.getId().toString());
            scheduleTask(task);
        }
    }

    /**
     * 取消任务。从scheduledTasks Map中获取任务的Future对象，并调用cancel()方法来取消任务
     *
     * @param taskId 任务ID
     */
    public void cancelTask(String taskId) {
        ScheduledFuture<?> future = scheduledTasks.get(taskId);
        if (future != null) {
            future.cancel(true);
            scheduledTasks.remove(taskId);
            log.info("Task '{}' cancelled", taskId);
        }
    }

    /**
     * 执行实际任务逻辑
     *
     * @param task {@link SubscribeMessageScheduledTaskEntity}
     */
    private void executeTask(SubscribeMessageScheduledTaskEntity task) {
        extracted(task);
    }

    private void extracted(SubscribeMessageScheduledTaskEntity task) {
        log.info("Executing task {} == {}", task.getId(), task.getTaskName());
        QueryWrapper<TaskTypeInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(TaskTypeInfoEntity.COL_ID, task.getTaskType());
        queryWrapper.eq(TaskTypeInfoEntity.COL_FLAG, true);
        TaskTypeInfoEntity taskTypeEntity = taskTypeInfoMapper.selectOne(queryWrapper);
        if (taskTypeEntity == null) {
            log.error("Task type not found, id: {}", task.getTaskType());
            return;
        }

        // Get the taskExecutor bean and execute the task
        String taskExecutorBeanName = taskTypeEntity.getTaskExecutorBeanName();
        TaskExecutor taskExecutor = applicationContext.getBean(taskExecutorBeanName, TaskExecutor.class);
        taskExecutor.execute(task);
    }

}
