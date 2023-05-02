package cn.ctrlcv.eighteen.scheduled.impl;

import cn.ctrlcv.eighteen.wechat.mapper.entity.SubscribeMessageScheduledTaskEntity;

/**
 * interface Name: Task
 * interface Description: 定义需要执行的具体任务
 *
 * @author liujm
 * @date 2023-05-02
 */
public interface TaskExecutor {


    /**
     * 执行任务接口
     *
     * @param task 要执行的任务内容
     */
    void execute(SubscribeMessageScheduledTaskEntity task);

}
