package zb.Team.showticket.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import zb.Team.showticket.user.application.SignInApplication;
import zb.Team.showticket.user.domain.SignInForm;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class SignInController {

    private final SignInApplication signInApplication;

    @GetMapping("/signin")
    public String signInUser(){
        return "/user/signin";
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signInUser(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.userLoginToken(form));
    }
}
