package zb.Team.showticket.user.controller;

import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "유저 회원가입", notes = "유저 회원 가입 진행.")
    @PostMapping("/signup")
    public ResponseEntity<String> userSignUp(@RequestBody UserSignUpForm form){

        return ResponseEntity.ok(signUpApplication.userSignUp(form));
    }

    @ApiOperation(value = "유저 이메일 인증", notes = "인증코드를 통한 가입 인증 진행.")
    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(String email,String code){
        signUpApplication.userVerify(email, code);
        return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
    }

}
