package cn.ctrlcv.eighteen.wechat.service.impl;

import cn.ctrlcv.eighteen.common.model.ApiResult;
import cn.ctrlcv.eighteen.client.WechatClient;
import cn.ctrlcv.eighteen.config.wx.AppletConfig;
import cn.ctrlcv.eighteen.config.wx.WechatApplet;
import cn.ctrlcv.eighteen.wechat.controller.user.dto.RegisterDTO;
import cn.ctrlcv.eighteen.wechat.controller.user.vo.LoginVO;
import cn.ctrlcv.eighteen.wechat.mapper.UsersMapper;
import cn.ctrlcv.eighteen.wechat.mapper.entity.UsersEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    @Mock
    private WechatClient mockWechatClient;
    @Mock
    private AppletConfig mockAppletConfig;
    @Mock
    private UsersMapper mockUsersMapper;

    @InjectMocks
    private LoginServiceImpl loginServiceImplUnderTest;

    @Test
    void testCheckLoginCode() {
        // Setup
        // Configure AppletConfig.getApplet(...).
        final WechatApplet wechatApplet = new WechatApplet();
        wechatApplet.setAppid("appid");
        wechatApplet.setAppSecret("appSecret");
        when(mockAppletConfig.getApplet()).thenReturn(wechatApplet);

        when(mockWechatClient.code2Session("appid", "appSecret", "code", "authorization_code"))
                .thenReturn(Mono.just("value"));

        // Configure UsersMapper.selectOne(...).
        final UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUserId(0);
        usersEntity.setOpenId("openId");
        usersEntity.setPhoneNumber("phoneNumber");
        usersEntity.setFullName("fullName");
        usersEntity.setNickname("nickname");
        usersEntity.setBirthDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        usersEntity.setLunarBirthday(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        usersEntity.setGender("gender");
        usersEntity.setAvatar("avatar");
        when(mockUsersMapper.selectOne(any(QueryWrapper.class))).thenReturn(usersEntity);

        // Run the test
        final Mono<ApiResult<LoginVO>> result = loginServiceImplUnderTest.checkLoginCode("code");

        // Verify the results
    }

    @Test
    void testCheckLoginCode_WechatClientReturnsNoItem() {
        // Setup
        // Configure AppletConfig.getApplet(...).
        final WechatApplet wechatApplet = new WechatApplet();
        wechatApplet.setAppid("appid");
        wechatApplet.setAppSecret("appSecret");
        when(mockAppletConfig.getApplet()).thenReturn(wechatApplet);

        when(mockWechatClient.code2Session("appid", "appSecret", "code", "authorization_code"))
                .thenReturn(Mono.empty());

        // Configure UsersMapper.selectOne(...).
        final UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUserId(0);
        usersEntity.setOpenId("openId");
        usersEntity.setPhoneNumber("phoneNumber");
        usersEntity.setFullName("fullName");
        usersEntity.setNickname("nickname");
        usersEntity.setBirthDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        usersEntity.setLunarBirthday(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        usersEntity.setGender("gender");
        usersEntity.setAvatar("avatar");
        when(mockUsersMapper.selectOne(any(QueryWrapper.class))).thenReturn(usersEntity);

        // Run the test
        final Mono<ApiResult<LoginVO>> result = loginServiceImplUnderTest.checkLoginCode("code");

        // Verify the results
    }

    @Test
    void testCheckLoginCode_WechatClientReturnsError() {
        // Setup
        // Configure AppletConfig.getApplet(...).
        final WechatApplet wechatApplet = new WechatApplet();
        wechatApplet.setAppid("appid");
        wechatApplet.setAppSecret("appSecret");
        when(mockAppletConfig.getApplet()).thenReturn(wechatApplet);

        when(mockWechatClient.code2Session("appid", "appSecret", "code", "authorization_code"))
                .thenReturn(Mono.error(new Exception("message")));

        // Configure UsersMapper.selectOne(...).
        final UsersEntity usersEntity = new UsersEntity();
        usersEntity.setUserId(0);
        usersEntity.setOpenId("openId");
        usersEntity.setPhoneNumber("phoneNumber");
        usersEntity.setFullName("fullName");
        usersEntity.setNickname("nickname");
        usersEntity.setBirthDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        usersEntity.setLunarBirthday(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        usersEntity.setGender("gender");
        usersEntity.setAvatar("avatar");
        when(mockUsersMapper.selectOne(any(QueryWrapper.class))).thenReturn(usersEntity);

        // Run the test
        final Mono<ApiResult<LoginVO>> result = loginServiceImplUnderTest.checkLoginCode("code");

        // Verify the results
    }

    @Test
    void testCheckLoginCode_UsersMapperReturnsNull() {
        // Setup
        // Configure AppletConfig.getApplet(...).
        final WechatApplet wechatApplet = new WechatApplet();
        wechatApplet.setAppid("appid");
        wechatApplet.setAppSecret("appSecret");
        when(mockAppletConfig.getApplet()).thenReturn(wechatApplet);

        when(mockWechatClient.code2Session("appid", "appSecret", "code", "authorization_code"))
                .thenReturn(Mono.just("value"));
        when(mockUsersMapper.selectOne(any(QueryWrapper.class))).thenReturn(null);

        // Run the test
        final Mono<ApiResult<LoginVO>> result = loginServiceImplUnderTest.checkLoginCode("code");

        // Verify the results
    }

    @Test
    void testRegister() {
        // Setup
        final RegisterDTO registerDTO = new RegisterDTO();
        registerDTO.setUserId(0);
        registerDTO.setOpenId("openId");
        registerDTO.setBirthdayType("n");
        registerDTO.setBirthday("1997-11-12");
        registerDTO.setNickname("nickname");
        registerDTO.setPhoneNumber("phoneNumber");
        registerDTO.setGender("gender");
        registerDTO.setFullName("fullName");

        // Run the test
        loginServiceImplUnderTest.register(registerDTO);

        // Verify the results

        // Setup
        final RegisterDTO registerDTO1 = new RegisterDTO();
        registerDTO1.setUserId(0);
        registerDTO1.setOpenId("openId");
        registerDTO1.setBirthdayType("y");
        registerDTO1.setBirthday("1997-12-11");
        registerDTO1.setNickname("nickname");
        registerDTO1.setPhoneNumber("phoneNumber");
        registerDTO1.setGender("gender");
        registerDTO1.setFullName("fullName");

        // Run the test
        loginServiceImplUnderTest.register(registerDTO1);

        // Verify the results
    }

}
