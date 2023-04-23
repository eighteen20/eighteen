package cn.ctrlcv.eighteen.config.mybatisplus;

import cn.ctrlcv.eighteen.base.model.BaseEntityField;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;

/**
 * Class Name: MyMetaObjectHandler
 * Class Description: 自定义填充公共字段
 *
 * @author liujm
 * @date 2023-04-23
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, BaseEntityField.COL_CREATED_AT, () -> LocalDateTime.now(Clock.systemUTC()), LocalDateTime.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, BaseEntityField.COL_UPDATED_AT, () -> LocalDateTime.now(Clock.systemUTC()), LocalDateTime.class);
    }

}
