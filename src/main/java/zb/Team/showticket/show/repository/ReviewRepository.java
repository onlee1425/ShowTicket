package zb.Team.showticket.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zb.Team.showticket.show.model.Review;
import zb.Team.showticket.show.model.Ticketing;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
