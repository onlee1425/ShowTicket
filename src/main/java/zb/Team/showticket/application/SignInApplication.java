package zb.Team.showticket.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zb.Team.showticket.auth.config.JwtAuthProvider;
import zb.Team.showticket.auth.domain.UserType;
import zb.Team.showticket.user.domain.SignInForm;
import zb.Team.showticket.user.domain.entity.Users;
import zb.Team.showticket.user.exception.CustomException;
import zb.Team.showticket.user.exception.ErrorCode;
import zb.Team.showticket.user.service.UserService;

@Service
@RequiredArgsConstructor
public class SignInApplication {
    private final UserService userService;
    private final JwtAuthProvider provider;

    // user
    public String userLoginToken(SignInForm form){
        Users u = userService.findValidUser(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        return provider.createToken(u.getEmail(),u.getId(), UserType.USER);
    }

}
