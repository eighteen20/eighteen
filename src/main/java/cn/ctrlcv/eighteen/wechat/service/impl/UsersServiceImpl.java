package cn.ctrlcv.eighteen.wechat.service.impl;

import cn.ctrlcv.eighteen.common.enums.ApiErrorEnum;
import cn.ctrlcv.eighteen.common.exception.CustomException;
import cn.ctrlcv.eighteen.common.model.UserToken;
import cn.ctrlcv.eighteen.wechat.controller.user.vo.UserInfoVO;
import cn.ctrlcv.eighteen.wechat.mapper.UsersMapper;
import cn.ctrlcv.eighteen.wechat.mapper.entity.UsersEntity;
import cn.ctrlcv.eighteen.wechat.service.IUsersService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Class Name: UsersServiceImpl
 * Class Description: 用户信息接口实现类
 *
 * @author liujm
 * @date 2023-05-03
 */
@Slf4j
@Service
public class UsersServiceImpl implements IUsersService {

    @Resource
    private UsersMapper usersMapper;

    @Override
    public UserInfoVO getBaseInfo(UserToken userToken) {
        QueryWrapper<UsersEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(UsersEntity.COL_USER_ID, userToken.getUserId());
        queryWrapper.eq(UsersEntity.COL_OPEN_ID, userToken.getOpenId());
        UsersEntity entity = this.usersMapper.selectOne(queryWrapper);
        if (entity != null) {
            UserInfoVO vo = new UserInfoVO();
            vo.setUserId(entity.getUserId());
            vo.setPhoneNumber(entity.getPhoneNumber());
            vo.setNickname(entity.getNickname());
            vo.setBirthDate(entity.getBirthDate());
            vo.setLunarBirthday(entity.getLunarBirthday());
            vo.setGender(entity.getGender().getCode());
            vo.setAvatar(entity.getAvatar());
            return vo;
        } else {
           throw new CustomException(ApiErrorEnum.USER_NOT_FOUND);
        }
    }
}
