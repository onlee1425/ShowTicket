package zb.Team.showticket.user.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zb.Team.showticket.auth.config.JwtAuthProvider;
import zb.Team.showticket.auth.domain.UserVo;
import zb.Team.showticket.user.domain.ChangeCreditForm;
import zb.Team.showticket.user.domain.entity.Users;
import zb.Team.showticket.user.domain.model.UserDto;
import zb.Team.showticket.user.exception.CustomException;
import zb.Team.showticket.user.exception.ErrorCode;
import zb.Team.showticket.user.service.UserCreditBalanceService;
import zb.Team.showticket.user.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtAuthProvider provider;
    private final UserService userService;
    private final UserCreditBalanceService userCreditBalanceService;

    @ApiOperation(value = "유저 정보 조회", notes = "유저의 정보를 조회한다.")
    @GetMapping("/getInfo")
    public ResponseEntity<UserDto> getInfo(@RequestHeader(name = "AUTH-TOKEN")String token){
        UserVo vo = provider.getUserVo(token);
        Users u = userService.findByIdAndEmail(vo.getId(), vo.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));

        return ResponseEntity.ok(UserDto.from(u));
    }

    @ApiOperation(value = "크레딧 관리", notes = "로그인 유저의 크레딧 관리.")
    @PostMapping("/balance")
    public ResponseEntity<Integer> changeCredit(@RequestHeader(name = "AUTH-TOKEN") String token,
                                                @RequestBody ChangeCreditForm form) {
        UserVo vo = provider.getUserVo(token);

        return ResponseEntity.ok(userCreditBalanceService.changeCredit(vo.getId(), form).getCurrentCredit());
    }
}
