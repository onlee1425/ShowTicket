package zb.Team.showticket.application;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import zb.Team.showticket.provider.domain.entity.Provider;
import zb.Team.showticket.provider.domain.ProviderSignUpForm;
import zb.Team.showticket.provider.service.ProviderSignUpService;
import zb.Team.showticket.user.component.MailComponent;
import zb.Team.showticket.user.domain.UserSignUpForm;
import zb.Team.showticket.user.domain.entity.Users;
import zb.Team.showticket.user.exception.CustomException;
import zb.Team.showticket.user.exception.ErrorCode;
import zb.Team.showticket.user.service.UserSignUpService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final MailComponent mailComponent;
    private final UserSignUpService userSignUpService;
    private final ProviderSignUpService providerSignUpService;


    // user 회원가입
    public String userSignUp(UserSignUpForm form) {
        if (userSignUpService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
            // exception
        } else {
            Users u = userSignUpService.signUp(form);
            LocalDateTime now = LocalDateTime.now();

            String code = getRandomCode();
            String email = form.getEmail();
            String subject = "보여주SHOW 사이트 회원 가입을 축하드립니다!";
            String text = getUserVerificationEmailBody(u.getEmail(),u.getName(),code);

            mailComponent.sendMail(email,subject,text);

            userSignUpService.ChangeUserValidationEmail(u.getId(), code);
            return "회원 가입에 성공하였습니다.";
        }
    }

    // provider 회원가입
    public String providerSignUp(ProviderSignUpForm form) {
        if (providerSignUpService.isEmailExist(form.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
            // exception
        } else {
            Provider p = providerSignUpService.signUp(form);
            LocalDateTime now = LocalDateTime.now();

            String code = getRandomCode();
            String email = form.getEmail();
            String subject = "보여주SHOW 사이트 가입을 축하드립니다!";
            String text = getProviderVerificationEmailBody(p.getEmail(),p.getName(),code);

            mailComponent.sendMail(email,subject,text);

            providerSignUpService.ChangeProviderValidationEmail(p.getId(), code);
            return "업체 가입에 성공하였습니다.";
        }
    }

    private String getRandomCode() {
        return RandomStringUtils.random(10, true, true);
    }

    public void userVerify(String email,String code){
        userSignUpService.verifyEmail(email,code);
    }

    public void providerVerify(String email,String code){
        providerSignUpService.ProviderVerifyEmail(email,code);
    }

    // 인증 메일 양식
    private String getUserVerificationEmailBody(String email, String name, String code) {
        StringBuilder builder = new StringBuilder();
        return builder.append("안녕하세요,").append(name).append("회원님! 아래 링크를 클릭하시어 이메일 인증을 완료해 주세요:)\n\n")
                .append("http://localhost:8080/user/verify?email=")
                .append(email)
                .append("&code=")

                .append(code).toString();

    }

    private String getProviderVerificationEmailBody(String email, String name, String code) {
        StringBuilder builder = new StringBuilder();
        return builder.append("안녕하세요,").append(name).append("님! 아래 링크를 클릭하시어 이메일 인증을 완료해 주세요:)\n 이메일 인증 완료 후 공연 등록이 가능합니다.\n\n")
                .append("http://localhost:8080/provider/verify?email=")
                .append(email)
                .append("&code=")

                .append(code).toString();

    }

}
