package zb.Team.showticket.provider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zb.Team.showticket.provider.domain.entity.Provider;
import zb.Team.showticket.provider.domain.repository.ProviderRepository;
import zb.Team.showticket.provider.domain.ProviderSignUpForm;
import zb.Team.showticket.user.exception.CustomException;
import zb.Team.showticket.user.exception.ErrorCode;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProviderSignUpService {

    private final ProviderRepository providerRepository;

    public Provider signUp(ProviderSignUpForm form){
        return providerRepository.save(Provider.from(form));
    }

    public boolean isEmailExist(String email){
        return providerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .isPresent();
    }

    //이메일 인증
    @Transactional
    public void ProviderVerifyEmail(String email,String code){
        Provider provider = providerRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_USER));
        if (provider.isVerify()){
            throw new CustomException(ErrorCode.ALREADY_VERIFY); //이미 인증완료
        }
        else if (!provider.getVerificationCode().equals(code)){
            throw new CustomException(ErrorCode.WRONG_VERIFICATION); //인증코드 불일치
        }
        else if (provider.getVerifyExpiredAt().isBefore(LocalDateTime.now())){
            throw new CustomException(ErrorCode.EXPIRE_CODE); //인증코드 만료
        }
        provider.setVerify(true);
    }

    //메일 확인을 통한 인증처리
    @Transactional
    public LocalDateTime ChangeProviderValidationEmail(Long customerId, String verificationCode) {
        Optional<Provider> providerOptional = providerRepository.findById(customerId);

        if (providerOptional.isPresent()) {
            Provider provider = providerOptional.get();
            provider.setVerificationCode(verificationCode);
            provider.setVerifyExpiredAt(LocalDateTime.now().plusDays(1)); //하루 뒤에 만료
            return provider.getVerifyExpiredAt();
        }
        //예외처리
        throw new CustomException(ErrorCode.NOT_FOUND_USER);
    }
}
