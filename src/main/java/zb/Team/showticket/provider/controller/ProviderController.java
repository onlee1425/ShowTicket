package zb.Team.showticket.provider.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zb.Team.showticket.auth.config.JwtAuthProvider;
import zb.Team.showticket.auth.domain.ProviderVo;
import zb.Team.showticket.provider.domain.entity.Provider;
import zb.Team.showticket.provider.domain.model.ProviderDto;
import zb.Team.showticket.provider.service.ProviderService;
import zb.Team.showticket.user.exception.CustomException;
import zb.Team.showticket.user.exception.ErrorCode;

@RestController
@RequestMapping("/provider")
@RequiredArgsConstructor
public class ProviderController {
    private final JwtAuthProvider provider;
    private final ProviderService providerService;

    @GetMapping("/getInfo")
    public ResponseEntity<ProviderDto> getInfo(@RequestHeader(name = "AUTH-TOKEN")String token){
        ProviderVo vo = provider.getProviderVo(token);
        Provider p = providerService.findByIdAndEmail(vo.getId(), vo.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        return ResponseEntity.ok(ProviderDto.from(p));
    }
}
