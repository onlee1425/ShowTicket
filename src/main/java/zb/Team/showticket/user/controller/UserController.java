package zb.Team.showticket.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zb.Team.showticket.auth.config.JwtAuthProvider;
import zb.Team.showticket.auth.domain.UserVo;
import zb.Team.showticket.user.domain.Users;
import zb.Team.showticket.user.domain.model.UserDto;
import zb.Team.showticket.user.exception.CustomException;
import zb.Team.showticket.user.exception.ErrorCode;
import zb.Team.showticket.user.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtAuthProvider provider;
    private final UserService userService;

    @GetMapping("/getInfo")
    public ResponseEntity<UserDto> getInfo(@RequestHeader(name = "AUTH-TOKEN")String token){
        UserVo vo = provider.getUserVo(token);
        Users u = userService.findByIdAndEmail(vo.getId(), vo.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        return ResponseEntity.ok(UserDto.from(u));
    }
}
