package cn.ctrlcv.eighteen.config.mybatisplus;

import cn.ctrlcv.eighteen.common.enums.FlagEnum;
import cn.ctrlcv.eighteen.common.model.BaseEntityField;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.util.Date;

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
        this.strictInsertFill(metaObject, BaseEntityField.COL_CREATED_AT, Date.class, new Date(Clock.systemUTC().millis()));
        this.strictInsertFill(metaObject, BaseEntityField.COL_FLAG, FlagEnum.class, FlagEnum.VALID);

        this.strictUpdateFill(metaObject, BaseEntityField.COL_UPDATED_AT, Date.class, new Date(Clock.systemUTC().millis()));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill(metaObject, BaseEntityField.COL_UPDATED_AT, Date.class, new Date(Clock.systemUTC().millis()));
    }

}
