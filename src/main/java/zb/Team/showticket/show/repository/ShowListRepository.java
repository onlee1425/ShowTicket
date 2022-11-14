package zb.Team.showticket.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zb.Team.showticket.show.model.ShowDetail;
import zb.Team.showticket.show.model.ShowList;

import java.util.Optional;

@Repository
public interface ShowListRepository extends JpaRepository<ShowList, Long> {
    Optional<ShowList> findByMt20id(String mt20id);
}
