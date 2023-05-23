package cn.ctrlcv.eighteen.wechat.service;

import cn.ctrlcv.eighteen.wechat.controller.couple.vo.CoupleRelationshipVO;

/**
 * interface Name: ICoupleService
 * interface Description: 情侣关系业务接口
 *
 * @author liujm
 * @date 2023-05-03
 */
public interface ICoupleService {

    /**
     * 获取情侣关系信息
     *
     * @param id 登录用户ID
     * @return {@link CoupleRelationshipVO}
     */
    CoupleRelationshipVO getInfo(Long id);
}
