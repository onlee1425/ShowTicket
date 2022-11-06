package zb.Team.showticket.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zb.Team.showticket.user.domain.UserSignUpForm;
import zb.Team.showticket.user.domain.Users;
import zb.Team.showticket.user.service.UserSignUpService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SignUpServiceTest {

    @Autowired
    private UserSignUpService userSignUpService;


    @Test
    void signUp() {
        UserSignUpForm form = UserSignUpForm.builder()
                .name("user1")
                .birth(LocalDate.now())
                .email("abc@naver.com")
                .password("1")
                .phone("010-0000-0000")
                .build();

        Users u = userSignUpService.signUp(form);
        assertNotNull(u.getId());
        assertNotNull(u.getCreateAt());
    }
}
