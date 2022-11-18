package zb.Team.showticket.user.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import zb.Team.showticket.user.domain.entity.UserCreditBalanceHistory;

import java.util.Optional;

@Repository
public interface UserCreditBalanceHistoryRepository extends JpaRepository<UserCreditBalanceHistory,Long> {
    Optional<UserCreditBalanceHistory> findFirstByUser_IdOrderByIdDesc(@RequestParam("user_id")Long userId);
}
