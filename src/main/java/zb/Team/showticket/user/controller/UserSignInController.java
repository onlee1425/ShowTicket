package zb.Team.showticket.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zb.Team.showticket.application.SignInApplication;
import zb.Team.showticket.user.domain.SignInForm;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserSignInController {

    private final SignInApplication signInApplication;

    @ApiOperation(value = "로그인", notes = "로그인을 통한 토큰 발급.")
    @PostMapping("/signin")
    public ResponseEntity<String> signInUser(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.userLoginToken(form));
    }
}
