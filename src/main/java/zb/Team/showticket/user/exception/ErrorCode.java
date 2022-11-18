package zb.Team.showticket.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    //signUp
    ALREADY_REGISTER_USER(HttpStatus.BAD_REQUEST,"이미 가입된 회원입니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST,"일치하는 회원이 없습니다."),
    ALREADY_VERIFY(HttpStatus.BAD_REQUEST,"이미 인증이 완료되었습니다"),
    WRONG_VERIFICATION(HttpStatus.BAD_REQUEST,"잘못된 인증 시도입니다."),
    EXPIRE_CODE(HttpStatus.BAD_REQUEST,"인증 시간이 만료되었습니다."),

    //signIn
    LOGIN_CHECK_FAIL(HttpStatus.BAD_REQUEST,"아이디 또는 패스워드가 일치하지 않습니다."),
    
    //credit
    NOT_ENOUGH_CREDIT(HttpStatus.BAD_REQUEST,"크레딧이 부족합니다."),

    //board
    WRONG_MATCH_USER(HttpStatus.BAD_REQUEST,"게시글 작성자가 아닙니다."),
    NOT_FOUND_REVIEWS(HttpStatus.BAD_REQUEST,"게시글이 존재하지 않습니다."),
    NOT_FOUND_BOARD(HttpStatus.BAD_REQUEST,"게시판이 존재하지 않습니다."),
    NOT_MACTH_USERTYPE(HttpStatus.BAD_REQUEST,"현재 유저타입으로는 해당 게시판 작성이 불가합니다"),
    ADD_ONLY_SHOW_BOARD(HttpStatus.BAD_REQUEST,"해당 게시판은 공연 게시글만 작성 가능합니다."),
    READ_ONLY_SHOW_BOARD(HttpStatus.BAD_REQUEST,"공연 게시판만 조회 가능합니다");
    private final HttpStatus httpStatus;
    private final String detail;

}
