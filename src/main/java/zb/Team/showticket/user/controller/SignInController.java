package zb.Team.showticket.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zb.Team.showticket.user.application.SignInApplication;
import zb.Team.showticket.user.domain.SignInForm;

@RestController
@RequestMapping("/signin")
@RequiredArgsConstructor
public class SignInController {

    private final SignInApplication signInApplication;

    @PostMapping("/user")
    public ResponseEntity<String> signInUsser(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.userLoginToken(form));
    }
}
