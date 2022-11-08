package zb.Team.showticket.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zb.Team.showticket.user.domain.ChangeCreditForm;
import zb.Team.showticket.user.domain.repository.UserCreditBalanceHistoryRepository;
import zb.Team.showticket.user.domain.repository.UserRepository;
import zb.Team.showticket.user.domain.model.UserCreditBalanceHistory;
import zb.Team.showticket.user.exception.CustomException;
import zb.Team.showticket.user.exception.ErrorCode;

@Service
@RequiredArgsConstructor
public class UserCreditBalanceService {
    private final UserCreditBalanceHistoryRepository creditRepo;
    private final UserRepository userRepository;

    @Transactional(noRollbackFor = {CustomException.class})
    public UserCreditBalanceHistory changeCredit(Long userid, ChangeCreditForm form) throws CustomException{
        UserCreditBalanceHistory userCreditBalanceHistory
                = creditRepo.findFirstByUser_IdOrderByIdDesc(userid)
                .orElse(UserCreditBalanceHistory.builder()
                        .changeCredit(0)
                        .currentCredit(0)
                        .user(userRepository.findById(userid)
                                .orElseThrow(()->new CustomException(ErrorCode.NOT_FOUND_USER)))
                        .build());
        if (userCreditBalanceHistory.getCurrentCredit() + form.getCredit() < 0){
            throw new CustomException(ErrorCode.NOT_ENOUGH_CREDIT);
        }

        //크레딧 변동
        userCreditBalanceHistory = UserCreditBalanceHistory.builder()
                .currentCredit(form.getCredit())
                .changeCredit(userCreditBalanceHistory.getCurrentCredit()+ form.getCredit())
                .description(form.getMessage())
                .user(userCreditBalanceHistory.getUser())
                .build();
        userCreditBalanceHistory.getUser().setCredit(userCreditBalanceHistory.getCurrentCredit());
        return creditRepo.save(userCreditBalanceHistory);
    }

}
