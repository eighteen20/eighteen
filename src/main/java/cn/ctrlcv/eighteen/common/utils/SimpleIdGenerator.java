package cn.ctrlcv.eighteen.common.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class Name: SimpleIdGenerator
 * Class Description: 适用于单机部署的ID生成工具类
 *
 * @author liujm
 * @date 2023-04-25
 */
public class SimpleIdGenerator {

    private static final int MAX_ID = 9999999;
    private static final AtomicInteger CURRENT_ID = new AtomicInteger(getRandomStartId());

    private SimpleIdGenerator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 生成一个随机的7位数ID。
     *
     * @return 一个随机的7位数ID
     */
    private static int getRandomStartId() {
        return (int) (Math.random() * MAX_ID);
    }

    /**
     * 获取下一个递增的7位数ID。
     *
     * @return 下一个递增的7位数ID
     */
    public static int nextId() {
        int nextId = CURRENT_ID.incrementAndGet();
        if (nextId > MAX_ID) {
            CURRENT_ID.set(0);
            nextId = CURRENT_ID.incrementAndGet();
        }
        return nextId;
    }

}
