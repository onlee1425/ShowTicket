package zb.Team.showticket.user.domain.model;

import lombok.*;
import org.apache.catalina.User;
import zb.Team.showticket.user.domain.BaseEntity;
import zb.Team.showticket.user.domain.Users;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreditBalanceHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Users.class, fetch = FetchType.LAZY)
    private Users user;

    //변경금액
    private Integer changeCredit;
    //현재잔액
    private Integer currentCredit;
    private String fromMessage;
    private String description;
}
