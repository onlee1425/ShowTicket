package zb.Team.showticket.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zb.Team.showticket.user.domain.SignUpForm;
import zb.Team.showticket.user.domain.UserRepository;
import zb.Team.showticket.user.domain.Users;
import zb.Team.showticket.user.exception.CustomException;
import zb.Team.showticket.user.exception.ErrorCode;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSignUpService {

    private final UserRepository userRepository;

    public Users signUp(SignUpForm form){
        return userRepository.save(Users.from(form));
    }

    public boolean isEmailExist(String email){
        return userRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }

    //이메일 인증
    @Transactional
    public void verifyEmail(String email,String code){
        Users users = userRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        if (users.isVerify()){
            throw new CustomException(ErrorCode.ALREADY_VERIFY); //이미 인증완료
        }
        else if (!users.getVerificationCode().equals(code)){
            throw new CustomException(ErrorCode.WRONG_VERIFICATION); //회원 - 인증코드 불일치
        }
        else if (users.getVerifyExpiredAt().isBefore(LocalDateTime.now())){
            throw new CustomException(ErrorCode.EXPIRE_CODE); //인증코드 만료
        }
        users.setVerify(true);
    }

    //메일 확인을 통한 인증처리
    @Transactional
    public LocalDateTime ChangeCustomerValidationEmail(Long customerId, String verificationCode) {
        Optional<Users> usersOptional = userRepository.findById(customerId);

        if (usersOptional.isPresent()) {
            Users users = usersOptional.get();
            users.setVerificationCode(verificationCode);
            users.setVerifyExpiredAt(LocalDateTime.now().plusDays(1)); //하루 뒤에 만료
            return users.getVerifyExpiredAt();
        }
        //예외처리
        throw new CustomException(ErrorCode.NOT_FOUND_USER);
    }


}
