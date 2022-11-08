package zb.Team.showticket.user.controller;

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

    @PostMapping("/signin")
    public ResponseEntity<String> signInUser(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.userLoginToken(form));
    }
}
