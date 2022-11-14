package zb.Team.showticket.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zb.Team.showticket.show.model.ShowDetail;

import java.util.Optional;

@Repository
public interface ShowDetailRepository extends JpaRepository<ShowDetail, Long> {

    Optional<ShowDetail> findByMt20id(String mt20id);
}
