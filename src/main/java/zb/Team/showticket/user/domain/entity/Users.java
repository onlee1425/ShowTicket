package zb.Team.showticket.user.domain.entity;

import lombok.*;
import org.hibernate.envers.AuditOverride;
import zb.Team.showticket.user.domain.UserSignUpForm;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Users extends BaseEntity{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;
    private String password;
    private String phone;
    private LocalDate birth;

    //email verify
    private LocalDateTime verifyExpiredAt;
    private String verificationCode;
    private boolean verify;

    //credit
    @Column(columnDefinition = "int default 0")
    private Integer credit;

    public static Users from(UserSignUpForm form){
        return Users.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .name(form.getName())
                .phone(form.getPhone())
                .birth(form.getBirth())
                .verify(false)
                .build();
    }
}
