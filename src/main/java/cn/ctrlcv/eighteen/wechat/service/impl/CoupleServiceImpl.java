package cn.ctrlcv.eighteen.wechat.service.impl;

import cn.ctrlcv.eighteen.common.utils.DateUtil;
import cn.ctrlcv.eighteen.wechat.controller.couple.vo.CoupleRelationshipVO;
import cn.ctrlcv.eighteen.wechat.mapper.CoupleRelationshipEntityMapper;
import cn.ctrlcv.eighteen.wechat.mapper.entity.CoupleRelationshipEntity;
import cn.ctrlcv.eighteen.wechat.service.ICoupleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.TimeZone;

/**
 * Class Name: CoupleServiceImpl
 * Class Description: 情侣关系业务实现类
 *
 * @author liujm
 * @date 2023-05-03
 */
@Service
public class CoupleServiceImpl implements ICoupleService {

    @Resource
    private CoupleRelationshipEntityMapper coupleRelationshipMapper;

    @Override
    public CoupleRelationshipVO getInfo(Long id) {
        QueryWrapper<CoupleRelationshipEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(CoupleRelationshipEntity.COL_BOYFRIEND_USER_ID, id);
        queryWrapper.or();
        queryWrapper.eq(CoupleRelationshipEntity.COL_GIRLFRIEND_USER_ID, id);
        CoupleRelationshipEntity entity = coupleRelationshipMapper.selectOne(queryWrapper);
        if (entity != null) {
            CoupleRelationshipVO vo = new CoupleRelationshipVO();
            vo.setCoupleRelationshipId(entity.getId());
            vo.setBoyfriendUserId(entity.getBoyfriendUserId());
            vo.setGirlfriendUserId(entity.getGirlfriendUserId());
            vo.setLoveDate(DateUtil.getGMT8Date(entity.getLoveDate()).getTime());
            return vo;
        }
        return new CoupleRelationshipVO();
    }
}
