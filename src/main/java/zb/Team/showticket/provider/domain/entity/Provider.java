package zb.Team.showticket.provider.domain.entity;

import lombok.*;
import org.hibernate.envers.AuditOverride;
import zb.Team.showticket.provider.domain.ProviderSignUpForm;
import zb.Team.showticket.user.domain.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Locale;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AuditOverride(forClass = BaseEntity.class)
public class Provider extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;
    private String password;
    private String phone;

    //email verify
    private LocalDateTime verifyExpiredAt;
    private String verificationCode;
    private boolean verify;

    public static Provider from(ProviderSignUpForm form){
        return Provider.builder()
                .email(form.getEmail().toLowerCase(Locale.ROOT))
                .password(form.getPassword())
                .name(form.getName())
                .phone(form.getPhone())
                .verify(false)
                .build();
    }
}
