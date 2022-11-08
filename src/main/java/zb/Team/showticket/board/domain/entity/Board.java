package zb.Team.showticket.board.domain.entity;

import lombok.*;
import org.hibernate.envers.AuditOverride;
import zb.Team.showticket.user.domain.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
//리뷰게시판
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;
    @Column(nullable = false)
    private String name;
}
