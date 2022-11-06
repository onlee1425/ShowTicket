package zb.Team.showticket.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zb.Team.showticket.auth.config.JwtAuthProvider;
import zb.Team.showticket.auth.domain.UserType;
import zb.Team.showticket.provider.domain.Provider;
import zb.Team.showticket.provider.service.ProviderService;
import zb.Team.showticket.user.domain.SignInForm;
import zb.Team.showticket.user.domain.Users;
import zb.Team.showticket.user.exception.CustomException;
import zb.Team.showticket.user.exception.ErrorCode;
import zb.Team.showticket.user.service.UserService;

@Service
@RequiredArgsConstructor
public class SignInApplication {
    private final UserService userService;
    private final ProviderService providerService;
    private final JwtAuthProvider provider;

    // user
    public String userLoginToken(SignInForm form){
        Users u = userService.findValidUser(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.LOGIN_CHECK_FAIL));
        return provider.createToken(u.getEmail(),u.getId(), UserType.USER);
    }

    // provider
    public String providerLoginToken(SignInForm form){
        Provider p = providerService.findValidProvider(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER.LOGIN_CHECK_FAIL));
        return provider.createToken(p.getEmail(),p.getId(),UserType.PROVIDER);
    }
}
