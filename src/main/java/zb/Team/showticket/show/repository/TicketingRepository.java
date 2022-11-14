package zb.Team.showticket.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zb.Team.showticket.show.model.ShowList;
import zb.Team.showticket.show.model.Ticketing;

@Repository
public interface TicketingRepository extends JpaRepository<Ticketing, Long> {
}
