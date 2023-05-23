package cn.ctrlcv.eighteen.wechat.controller.user;

import cn.ctrlcv.eighteen.annotations.CurrentUser;
import cn.ctrlcv.eighteen.common.model.ApiResult;
import cn.ctrlcv.eighteen.common.model.UserToken;
import cn.ctrlcv.eighteen.wechat.controller.user.vo.UserInfoVO;
import cn.ctrlcv.eighteen.wechat.service.IUsersService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class Name: UsersController
 * Class Description: 用户信息控制器
 *
 * @author liujm
 * @date 2023-05-03
 */
@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    @Resource
    private IUsersService usersService;

    /**
     * 获取用户基本信息
     *
     * @param userToken 当前登录的用户信息
     * @return {@link UserToken}
     */
    @GetMapping("baseInfo")
    public ApiResult<UserInfoVO> getBaseInfo(@CurrentUser UserToken userToken) {
        return ApiResult.success(usersService.getBaseInfo(userToken));
    }
}
