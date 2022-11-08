package zb.Team.showticket.provider.controller;

import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "업체 회원가입", notes = "업체 회원가입 진행.")
    @PostMapping("/signup")
    public ResponseEntity<String> providerSignUp(@RequestBody ProviderSignUpForm form){
        return ResponseEntity.ok(signUpApplication.providerSignUp(form));
    }

    @ApiOperation(value = "업체 이메일 인증", notes = "인증코드를 통한 가입 인증 진행.")
    @GetMapping("/verify")
    public ResponseEntity<String> verifyProvider(String email,String code){
        signUpApplication.providerVerify(email,code);
        return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
    }
}
