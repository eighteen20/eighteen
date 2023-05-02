package cn.ctrlcv.eighteen.wechat.service;

import cn.ctrlcv.eighteen.common.model.ApiResult;
import cn.ctrlcv.eighteen.wechat.controller.user.dto.RegisterDTO;
import cn.ctrlcv.eighteen.wechat.controller.user.vo.LoginVO;
import reactor.core.publisher.Mono;

/**
 * interface Name: ILoginService
 * interface Description: 登录相关服务
 *
 * @author liujm
 * @date 2023-04-24
 */
public interface ILoginService {

    /**
     * 通过微信登录凭证获取openID
     *
     * @param code 登录凭证
     * @return {@link LoginVO}
     */
    Mono<ApiResult<LoginVO>> checkLoginCode(String code);

    /**
     * 补充资料(注册)
     *
     * @param registerDTO {@link RegisterDTO}
     * @return 提示语
     */
    ApiResult<String> register(RegisterDTO registerDTO);

    /**
     * 刷新token
     *
     * @param token token
     * @return 新token
     */
    ApiResult<String> refreshToken(String token);
}
