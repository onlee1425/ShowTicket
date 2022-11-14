package zb.Team.showticket.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zb.Team.showticket.show.model.Credit;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

}
