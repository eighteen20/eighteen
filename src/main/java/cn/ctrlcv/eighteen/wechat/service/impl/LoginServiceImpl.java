package cn.ctrlcv.eighteen.wechat.service.impl;

import cn.ctrlcv.eighteen.common.constant.SysConstant;
import cn.ctrlcv.eighteen.common.enums.ApiErrorEnum;
import cn.ctrlcv.eighteen.common.enums.FlagEnum;
import cn.ctrlcv.eighteen.common.enums.GenderEnum;
import cn.ctrlcv.eighteen.common.exception.CustomException;
import cn.ctrlcv.eighteen.common.model.ApiResult;
import cn.ctrlcv.eighteen.common.model.UserToken;
import cn.ctrlcv.eighteen.common.utils.DateUtil;
import cn.ctrlcv.eighteen.common.utils.JwtUtil;
import cn.ctrlcv.eighteen.common.utils.SimpleIdGenerator;
import cn.ctrlcv.eighteen.client.WechatClient;
import cn.ctrlcv.eighteen.client.response.Code2SessionResponse;
import cn.ctrlcv.eighteen.config.wx.AppletConfig;
import cn.ctrlcv.eighteen.wechat.controller.user.dto.RegisterDTO;
import cn.ctrlcv.eighteen.wechat.controller.user.vo.LoginVO;
import cn.ctrlcv.eighteen.wechat.mapper.UsersMapper;
import cn.ctrlcv.eighteen.wechat.mapper.entity.UsersEntity;
import cn.ctrlcv.eighteen.wechat.service.ILoginService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.time.Clock;
import java.util.Date;

/**
 * Class Name: ImplLoginService
 * Class Description: 登录相关服务实现
 *
 * @author liujm
 * @date 2023-04-24
 */
@Slf4j
@Service
public class LoginServiceImpl implements ILoginService {

    @Resource
    private WechatClient wechatClient;
    @Resource
    private AppletConfig appletConfig;
    @Resource
    private UsersMapper usersMapper;

    @Override
    public Mono<ApiResult<LoginVO>> checkLoginCode(String code) {
        return wechatClient.code2Session(appletConfig.getApplet().getAppid(), appletConfig.getApplet().getAppSecret(),
                        code, "authorization_code")
                .flatMap(data -> {
                    log.info("微信登陆凭证校验结果: {}", data);
                    ObjectMapper objectMapper = new ObjectMapper();
                    Code2SessionResponse sessionDTO;
                    try {
                        sessionDTO = objectMapper.readValue(data, Code2SessionResponse.class);
                    } catch (JsonProcessingException e) {
                        return Mono.error(new CustomException(ApiErrorEnum.FAILED_TO_OBTAIN_OPENID));
                    }

                    QueryWrapper<UsersEntity> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq(UsersEntity.COL_OPEN_ID, sessionDTO.getOpenid());
                    queryWrapper.eq(UsersEntity.COL_FLAG, true);
                    UsersEntity usersEntity = usersMapper.selectOne(queryWrapper);

                    LoginVO loginVO = new LoginVO();

                    UserToken userToken = new UserToken();
                    if (usersEntity == null) {
                        userToken.setUserId(SimpleIdGenerator.nextId());
                        loginVO.setIsNewUser(true);
                        userToken.setNickname("");
                    } else {
                        userToken.setUserId(usersEntity.getUserId());
                        loginVO.setIsNewUser(false);
                        userToken.setNickname(usersEntity.getNickname());

                        Assert.isTrue(sessionDTO.getOpenid().equals(usersEntity.getOpenId()), "用户信息不匹配");
                    }

                    userToken.setOpenId(sessionDTO.getOpenid());

                    String token;
                    token = JwtUtil.generateToken(userToken);


                    loginVO.setToken(token);

                    return Mono.just(ApiResult.success(loginVO));
                });
    }


    @Override
    public ApiResult<String> register(RegisterDTO registerDTO) {
        UsersEntity entity = new UsersEntity();
        entity.setUserId(registerDTO.getUserId());
        entity.setOpenId(registerDTO.getOpenId());
        entity.setPhoneNumber(registerDTO.getPhoneNumber());
        entity.setFullName(registerDTO.getFullName());
        entity.setNickname(registerDTO.getNickname());

        if (GenderEnum.MAN.getDesc().equals(registerDTO.getGender())) {
            entity.setAvatar("https://eighteen-1309533679.cos.ap-nanjing.myqcloud.com/%E5%A4%B4%E5%83%8F-%E7%94%B7%E5%85%AD.png");
        }

        if (GenderEnum.WOMAN.getDesc().equals(registerDTO.getGender())) {
            entity.setAvatar("https://eighteen-1309533679.cos.ap-nanjing.myqcloud.com/%E5%A4%B4%E5%83%8F-%E5%A5%B3%E4%B8%80.png");
        }


        try {
            if (SysConstant.GREGORIAN_BIRTHDAY.equals(registerDTO.getBirthdayType())) {
                // 阳历转阴历
                Solar solar = Solar.fromDate(DateUtil.stringToDate(registerDTO.getBirthday()));
                Lunar lunar = solar.getLunar();
                log.info("阳历转阴历: {}", lunar.getYear() + "-" + lunar.getMonth() + "-" + lunar.getDay());
                entity.setBirthDate(DateUtil.stringToDate(registerDTO.getBirthday()));
                entity.setLunarBirthday(DateUtil.stringToDate(lunar.getYear() + "-" + lunar.getMonth() + "-" + lunar.getDay()));
            } else if (SysConstant.LUNAR_BIRTHDAY.equals(registerDTO.getBirthdayType())) {
                // 阴历转阳历
                int[] ints = DateUtil.dateStringToIntArray(registerDTO.getBirthday());
                Lunar lunar = Lunar.fromYmd(ints[0], ints[1], ints[2]);
                Solar solar = lunar.getSolar();
                log.info("阴历转阳历: {}", solar.getYear() + "-" + solar.getMonth() + "-" + solar.getDay());
                entity.setLunarBirthday(DateUtil.stringToDate(registerDTO.getBirthday()));
                entity.setBirthDate(DateUtil.stringToDate(solar.getYear() + "-" + solar.getMonth() + "-" + solar.getDay()));
            } else {
                return ApiResult.error(ApiErrorEnum.BAD_REQUEST);
            }
        } catch (ParseException e) {
            log.error("日期转换异常", e);
            throw new CustomException(ApiErrorEnum.INTERNAL_SERVER_ERROR);
        }

        entity.setGender(GenderEnum.valueOf(registerDTO.getGender()));
        entity.setFlag(FlagEnum.VALID);
        entity.setCreatedBy(registerDTO.getUserId().toString());
        entity.setUpdatedBy(registerDTO.getUserId().toString());
        entity.setCreatedAt(new Date(Clock.systemUTC().millis()));
        entity.setUpdatedAt(entity.getCreatedAt());
        int insert = usersMapper.insert(entity);
        if (insert > 0) {
            return ApiResult.success("注册成功");
        }
        return ApiResult.error(ApiErrorEnum.INTERNAL_SERVER_ERROR, "注册失败");
    }


    @Override
    public ApiResult<String> refreshToken(String currentToken) {
        try {
            // 验证现有令牌
            if (JwtUtil.validateToken(currentToken)) {
                // 生成新令牌
                UserToken userToken = JwtUtil.getUserFromToken(currentToken);
                String newToken = JwtUtil.generateToken(userToken);

                // 返回新令牌
                return ApiResult.success(newToken);
            }
        } catch (Exception e) {
            // 处理令牌验证失败的情况

        }

        return ApiResult.error(ApiErrorEnum.LOGIN_EXPIRED);
    }
}

