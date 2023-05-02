package cn.ctrlcv.eighteen.wechat.service.impl;

import cn.ctrlcv.eighteen.wechat.controller.user.dto.RegisterDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {



    @InjectMocks
    private LoginServiceImpl loginServiceImplUnderTest;



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
