package cn.ctrlcv.eighteen.common.utils;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Class Name: ObjectUtils
 * Class Description: 对象工具类
 *
 * @author liujm
 * @date 2023-04-25
 */
public class ObjectUtils {

    /**
     * 将对象转换为不可变Map
     *
     * @param object 对象
     * @return 不可变Map
     * @param <T> 对象类型
     */
    public static <T> Map<String, Object> objToImmutableMap(T object) {
        return objectToMap(object, true);
    }

    /**
     * 将对象转换为Map
     *
     * @param object 对象
     * @return 不可变Map
     * @param <T> 对象类型
     */
    public static <T> Map<String, Object> objToMap(T object) {
        return objectToMap(object, false);
    }

    private static <T> Map<String, Object> objectToMap(T object, boolean immutable) {
        if (object == null) {
            return immutable ? ImmutableMap.of() : Maps.newHashMap();
        }

        ImmutableMap.Builder<String, Object> immutableMapBuilder = immutable ? ImmutableMap.builder() : null;
        Map<String, Object> mutableMap = !immutable ? Maps.newHashMap() : null;

        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                if (immutable) {
                    immutableMapBuilder.put(field.getName(), value == null ? "" : value);
                } else {
                    mutableMap.put(field.getName(), value == null ? "" : value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Error occurred while converting object to map", e);
            }
        }

        return immutable ? immutableMapBuilder.build() : mutableMap;
    }

}
