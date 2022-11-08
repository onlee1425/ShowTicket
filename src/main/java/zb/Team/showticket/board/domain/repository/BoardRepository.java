package zb.Team.showticket.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zb.Team.showticket.board.domain.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board,Long> {
     Board findByName(String name);
}
