package zb.Team.showticket.provider.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zb.Team.showticket.application.SignUpApplication;
import zb.Team.showticket.provider.domain.ProviderSignUpForm;

@RestController
@RequiredArgsConstructor
@RequestMapping("/provider")
public class ProviderSignUpController {
    private final SignUpApplication signUpApplication;

    @PostMapping("/signup")
    public ResponseEntity<String> providerSignUp(@RequestBody ProviderSignUpForm form){
        return ResponseEntity.ok(signUpApplication.providerSignUp(form));
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyProvider(String email,String code){
        signUpApplication.providerVerify(email,code);
        return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
    }
}
