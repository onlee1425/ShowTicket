package zb.Team.showticket.provider.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zb.Team.showticket.application.SignInApplication;
import zb.Team.showticket.user.domain.SignInForm;

@RestController
@RequestMapping("/provider")
@RequiredArgsConstructor
public class ProviderSignInController {
    private final SignInApplication signInApplication;

    @PostMapping("/signin")
    public ResponseEntity<String> signInProvider(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.providerLoginToken(form));
    }
}
