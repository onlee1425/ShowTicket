package zb.Team.showticket.provider.domain;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProviderSignUpForm {
    private String email;
    private String name;
    private String password;
    private String phone;
}
