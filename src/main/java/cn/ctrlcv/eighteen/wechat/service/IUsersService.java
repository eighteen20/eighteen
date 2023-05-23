package cn.ctrlcv.eighteen.wechat.service;

import cn.ctrlcv.eighteen.common.model.UserToken;
import cn.ctrlcv.eighteen.wechat.controller.user.vo.UserInfoVO;

/**
 * interface Name: IUsersService
 * interface Description: 用户信息接口
 *
 * @author liujm
 * @date 2023-05-03
 */
public interface IUsersService {

    /**
     * 获取用户基本信息
     *
     * @param userToken 当前登录的用户信息
     * @return {@link UserInfoVO}
     */
    UserInfoVO getBaseInfo(UserToken userToken);
}
