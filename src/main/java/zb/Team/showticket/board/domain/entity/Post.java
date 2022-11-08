package zb.Team.showticket.board.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditOverride;
import zb.Team.showticket.user.domain.entity.BaseEntity;
import zb.Team.showticket.user.domain.entity.Users;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
//리뷰게시글
public class Post extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long postId;
        @Column(nullable = false, length = 100)
        private String title;
        @Column(length = 500)
        private String content;

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "board_id")
        private Board board; // 게시글 - 게시판의 관계 - N:1


        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "user_id")
        private Users user;  // 게시글 - 회원의 관계 - N:1

        protected Board getBoard() {
            return board;
        }

        // 생성자
        public Post(Users user,Board board, String title, String content) {
            this.user = user;
            this.board = board;
            this.title = title;
            this.content = content;
        }

        // 수정시 데이터 처리
        public Post setUpdate(String title, String content) {
            this.title = title;
            this.content = content;
            return this;
        }
}
