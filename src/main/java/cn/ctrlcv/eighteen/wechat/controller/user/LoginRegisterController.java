package cn.ctrlcv.eighteen.wechat.controller.user;

import cn.ctrlcv.eighteen.annotations.CurrentUser;
import cn.ctrlcv.eighteen.annotations.SkipTokenCheck;
import cn.ctrlcv.eighteen.common.constant.SysConstant;
import cn.ctrlcv.eighteen.common.model.ApiResult;
import cn.ctrlcv.eighteen.common.model.UserToken;
import cn.ctrlcv.eighteen.wechat.controller.user.dto.RegisterDTO;
import cn.ctrlcv.eighteen.wechat.controller.user.vo.LoginVO;
import cn.ctrlcv.eighteen.wechat.service.ILoginService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * Class Name: LoginRegisterController
 * Class Description: 登录注册控制器
 *
 * @author liujm
 * @date 2023-04-24
 */
@RestController
@RequestMapping("v1/loginRegister/")
public class LoginRegisterController {

    @SkipTokenCheck
    @GetMapping("test")
    public String test() {
        return "love";
    }

    @Qualifier("loginServiceImpl")
    @Resource
    private ILoginService loginService;


    /**
     * 微信登录凭证校验接口
     *
     * @param code 小程序登录凭证
     * @return {@link LoginVO}
     */
    @SkipTokenCheck
    @PostMapping("checkLoginCode")
    public Mono<ApiResult<LoginVO>> checkLoginCode(@RequestParam String code) {
        return loginService.checkLoginCode(code);
    }

    /**
     * 新用户补充资料(注册)接口
     *
     * @param registerDTO {@link RegisterDTO}
     * @param token {@link UserToken}
     * @return
     */
    @PostMapping("register")
        public ApiResult<String> register(@RequestBody RegisterDTO registerDTO, @CurrentUser UserToken token) {
        registerDTO.setOpenId(token.getOpenId());
        registerDTO.setUserId(token.getUserId());
        return loginService.register(registerDTO);
    }


    /**
     * 刷新token接口
     *
     * @param token token
     */
    @PutMapping("refreshToken")
    public ApiResult<String> refreshToken(@RequestHeader(SysConstant.AUTHORIZATION) String token) {
        return loginService.refreshToken(token);
    }

}
