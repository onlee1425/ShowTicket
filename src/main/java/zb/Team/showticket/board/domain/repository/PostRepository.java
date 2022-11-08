package zb.Team.showticket.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zb.Team.showticket.board.domain.entity.Board;
import zb.Team.showticket.board.domain.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    // 게시판이름으로 게시글 리스트 조회
    List<Post> findByBoard(Board board);

}
