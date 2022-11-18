package zb.Team.showticket.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zb.Team.showticket.board.domain.entity.Board;
import zb.Team.showticket.board.domain.entity.ShowPost;

import java.util.List;

@Repository
public interface ShowPostRepository extends JpaRepository<ShowPost,Long> {

    List<ShowPost> findByBoard(Board board);
}
