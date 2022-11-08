package zb.Team.showticket.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zb.Team.showticket.application.SignUpApplication;
import zb.Team.showticket.user.domain.UserSignUpForm;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserSignUpController {

    private final SignUpApplication signUpApplication;

    @PostMapping("/signup")
    public ResponseEntity<String> userSignUp(@RequestBody UserSignUpForm form){

        return ResponseEntity.ok(signUpApplication.userSignUp(form));
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(String email,String code){
        signUpApplication.userVerify(email, code);
        return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
    }

}
