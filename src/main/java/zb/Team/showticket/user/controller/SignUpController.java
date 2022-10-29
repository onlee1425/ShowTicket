package zb.Team.showticket.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zb.Team.showticket.user.application.SignUpApplication;
import zb.Team.showticket.user.domain.SignUpForm;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final SignUpApplication signUpApplication;

    @PostMapping("/user")
    public ResponseEntity<String> userSignUp(@RequestBody SignUpForm form){
        return ResponseEntity.ok(signUpApplication.userSignUp(form));
    }

    @GetMapping("/user/verify")
    public ResponseEntity<String> verifyUser(String email,String code){
        signUpApplication.userVerify(email, code);
        return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
    }

}
