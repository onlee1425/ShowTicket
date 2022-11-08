package zb.Team.showticket.provider.controller;

import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "업체 로그인", notes = "로그인을 통한 토큰발급.")
    @PostMapping("/signin")
    public ResponseEntity<String> signInProvider(@RequestBody SignInForm form){
        return ResponseEntity.ok(signInApplication.providerLoginToken(form));
    }
}
